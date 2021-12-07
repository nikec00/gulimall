package com.atguigu.gulimall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.search.config.GulimallElasticSearchConfig;
import com.atguigu.gulimall.search.constant.EsConstant;
import com.atguigu.gulimall.search.feign.ProductFeignService;
import com.atguigu.gulimall.search.service.MallSearchService;
import com.atguigu.gulimall.search.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/6 18:56
 */
@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ProductFeignService feignService;

    @Override
    public SearchResult search(SearchParam searchParam) {
        //1.动态构建出查询所需要的dsl语句
        SearchResult searchResult = null;
        SearchRequest searchRequest = buildSearchRequest(searchParam);
        try {
            // 2.执行检索请求
            SearchResponse response = client.search(searchRequest, GulimallElasticSearchConfig.COMMON_OPTIONS);
            // 3.分析响应数据封装到我们需要的格式
            searchResult = buildSearchResult(response, searchParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResult;
    }

    /**
     * 准备检索请求
     * #模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存），排序，分页，高亮，聚合分析
     *
     * @return
     */
    private SearchRequest buildSearchRequest(SearchParam searchParam) {
        // 构建DSL语句
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        /**
         * 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存）
         */
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        String keyword = searchParam.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            boolQuery.must(QueryBuilders.matchQuery("skuTitle", keyword));
        }
        Long catalog3Id = searchParam.getCatalog3Id();
        if (catalog3Id != null) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", catalog3Id));
        }
        List<Long> brandIds = searchParam.getBrandId();
        if (brandIds != null && brandIds.size() > 0) {
            boolQuery.filter(QueryBuilders.termsQuery("brandId", brandIds));
        }
        Integer hasStock = searchParam.getHasStock();
        if (hasStock != null) {
            boolQuery.filter(QueryBuilders.termQuery("hasStock", hasStock == 1));
        }
        String skuPrice = searchParam.getSkuPrice();
        if (!StringUtils.isEmpty(skuPrice)) {
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            String[] price = skuPrice.split("_");
            if (price.length == 2) {
                rangeQuery.gte(price[0]).lte(price[1]);
            } else if (price.length == 1) {
                if (skuPrice.startsWith("_")) {
                    rangeQuery.lte(price[1]);
                }
                if (skuPrice.endsWith("_")) {
                    rangeQuery.gte(price[0]);
                }
            }
            boolQuery.filter(rangeQuery);
        }
        List<String> attrs = searchParam.getAttrs();
        if (attrs != null && attrs.size() > 0) {
            for (String attr : attrs) {
                BoolQueryBuilder query = QueryBuilders.boolQuery();
                // attr = 1_5寸:8寸
                String[] s = attr.split("_");
                String attrId = s[0];
                String[] attrValues = s[1].split(":");
                query.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                query.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", query, ScoreMode.None);
                boolQuery.filter(nestedQuery);
            }
        }
        sourceBuilder.query(boolQuery);
        /**
         * 排序，分页，高亮
         */
        String sort = searchParam.getSort();
        if (!StringUtils.isEmpty(sort)) {
            String[] s = sort.split("_");
            SortOrder sortOrder = SortOrder.fromString(s[1]);
            sourceBuilder.sort(s[0], sortOrder);
        }
        sourceBuilder.from((searchParam.getPageNum() - 1) * EsConstant.PRODUCT_PAGESIZE);
        sourceBuilder.size(EsConstant.PRODUCT_PAGESIZE);
        if (!StringUtils.isEmpty(keyword)) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.postTags("</b>");
            highlightBuilder.preTags("<b style='color:red'>");
            sourceBuilder.highlighter(highlightBuilder);
        }
        /**
         * 聚合分析
         */
        // 品牌聚合
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg").field("brandId").size(100);
        // 品牌聚合的子聚合
        brand_agg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        brand_agg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        sourceBuilder.aggregation(brand_agg);
        // 分类聚合
        TermsAggregationBuilder catalog_agg = AggregationBuilders.terms("catelog_agg").field("catalogId").size(20);
        catalog_agg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        sourceBuilder.aggregation(catalog_agg);
        // 属性聚合(嵌入式)
        NestedAggregationBuilder nested_agg = AggregationBuilders.nested("attr_agg", "attrs");
        TermsAggregationBuilder attr_id_agg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId").size(100);
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(10));
        nested_agg.subAggregation(attr_id_agg);
        sourceBuilder.aggregation(nested_agg);


        String s = sourceBuilder.toString();
        System.out.println("DSL语句：" + s);
        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, sourceBuilder);
        return searchRequest;
    }

    /**
     * 构建结果数据
     *
     * @param response
     * @return
     */
    private SearchResult buildSearchResult(SearchResponse response, SearchParam searchParam) {
        SearchResult result = new SearchResult();
        //1.返回所有查询到的商品
        SearchHits hits = response.getHits();
        List<SkuEsModel> skuList = new ArrayList<>();
        if (hits.getHits() != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits.getHits()) {
                String asString = hit.getSourceAsString();
                SkuEsModel esModel = JSON.parseObject(asString, SkuEsModel.class);
                if (!StringUtils.isEmpty(searchParam.getKeyword())) {
                    HighlightField skuTitle = hit.getHighlightFields().get("skuTitle");
                    String string = skuTitle.getFragments()[0].string();
                    esModel.setSkuTitle(string);
                }
                skuList.add(esModel);
            }
        }
        result.setProducts(skuList);
        //2.当前所有商品涉及到的所有属性信息
        List<AttrVo> attrVos = new ArrayList<>();
        ParsedNested attr_agg = response.getAggregations().get("attr_agg");
        ParsedLongTerms attr_id_agg = attr_agg.getAggregations().get("attr_id_agg");
        for (Terms.Bucket bucket : attr_id_agg.getBuckets()) {
            AttrVo attrVo = new AttrVo();
            long attrId = bucket.getKeyAsNumber().longValue();
            attrVo.setAttrId(attrId);
            ParsedStringTerms attr_name_agg = bucket.getAggregations().get("attr_name_agg");
            String attrName = attr_name_agg.getBuckets().get(0).getKeyAsString();
            attrVo.setAttrName(attrName);
            ParsedStringTerms attr_value_agg = bucket.getAggregations().get("attr_value_agg");
            List<String> collect = attr_value_agg.getBuckets().stream().map(item -> {
                String keyAsString = ((Terms.Bucket) item).getKeyAsString();
                return keyAsString;
            }).collect(Collectors.toList());
            attrVo.setAttrValue(collect);
            attrVos.add(attrVo);
        }
        result.setAttrs(attrVos);
//        //3.当前所有商品涉及到的品牌信息
        List<BrandVo> brandVos = new ArrayList<>();
        ParsedLongTerms brand_agg = response.getAggregations().get("brand_agg");
        for (Terms.Bucket bucket : brand_agg.getBuckets()) {
            BrandVo brandVo = new BrandVo();
            // 得到品牌的id
            String brandId = bucket.getKeyAsString();
            brandVo.setBrandId(Long.parseLong(brandId));
            ParsedStringTerms brand_img_agg = bucket.getAggregations().get("brand_img_agg");
            String img = brand_img_agg.getBuckets().get(0).getKeyAsString();
            brandVo.setBrandImg(img);
            ParsedStringTerms brand_name_agg = bucket.getAggregations().get("brand_name_agg");
            String brandName = brand_name_agg.getBuckets().get(0).getKeyAsString();
            brandVo.setBrandName(brandName);
            brandVos.add(brandVo);
        }
        result.setBrands(brandVos);
//        //4.当前所有商品涉及到的所有分类信息
        ParsedLongTerms catalog_agg = response.getAggregations().get("catelog_agg");
        List<CatalogVo> catalogVos = new ArrayList<>();

        for (Terms.Bucket bucket : catalog_agg.getBuckets()) {
            CatalogVo catalogVo = new CatalogVo();
            catalogVo.setCatalogId((Long.parseLong(bucket.getKeyAsString())));
            ParsedStringTerms catalog_name_agg = bucket.getAggregations().get("catalog_name_agg");
            String catalogName = catalog_name_agg.getBuckets().get(0).getKeyAsString();
            catalogVo.setCatalogName(catalogName);
            catalogVos.add(catalogVo);
        }
        result.setCatalogs(catalogVos);
        //5.分页信息，页码
        result.setPageNum(searchParam.getPageNum());
        //6.分页信息：总记录数
        long total = hits.getTotalHits().value;
        result.setTotal(total);
        //7.分页信息：总页码
        int totalPages = (int) (total % EsConstant.PRODUCT_PAGESIZE == 0 ? total / EsConstant.PRODUCT_PAGESIZE : (total / EsConstant.PRODUCT_PAGESIZE + 1));
        result.setTotalPages(totalPages);
        List<Integer> pageNavs = new ArrayList<>();
        for (int i = 1; i < totalPages; i++) {
            pageNavs.add(i);
        }
        result.setPageNavs(pageNavs);
        return result;
    }


}
