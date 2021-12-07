package com.atguigu.gulimall.search.vo;

import com.atguigu.common.to.es.SkuEsModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/6 19:22
 */
@Data
public class SearchResult implements Serializable {
    private static final long serialVersionUID = -5924084829677151210L;

    /**
     * 查询到的所有商品信息
     */
    private List<SkuEsModel> products;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页码
     */
    private Integer totalPages;

    private List<Integer> pageNavs;

    /**
     * 涉及品牌
     */
    private List<BrandVo> brands;

    /**
     * 所涉及到的所有属性
     */
    private List<AttrVo> attrs;

    /**
     * 涉及到的所有分类
     */
    private List<CatalogVo> catalogs;

    /**
     * 面包屑导航数据
     */
    private List<NavVo> navs;


}
