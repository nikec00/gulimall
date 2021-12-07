package com.atguigu.gulimall.search.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description：封装页面所有可能转递过来的条件
 * @Author: nkc
 * @Date: 2021/12/6 18:54
 */
@Data
public class SearchParam implements Serializable {
    private static final long serialVersionUID = -3872509475018913234L;

    /**
     * 全文匹配关键字
     */
    private String keyword;

    /**
     * 三级分类的id
     */
    private Long catalog3Id;

    /**
     * 排序条件
     * sort=saleCount_asc/desc  库存
     * sort=skuPrice_asc/desc   价格
     * sort=hotScore_asc/desc   热度评分
     */
    private String sort;

    /**
     * hasStock（是否有货），skuPrice区间，brandId，catalog3Id，attrs
     * hasStock=0/1
     * skuPrice=1_500,_500/500_
     * brandId=1
     * attrs=2_5寸：6寸
     */
    /**
     * 是否只显示有货
     */
    private Integer hasStock;

    /**
     * 价格区间查询
     */
    private String skuPrice;

    /**
     * 按照品牌进行查询，可以多选
     */
    private List<Long> brandId;

    /**
     * 按照属性进行筛选
     */
    private List<String> attrs;

    /**
     * 页码
     */
    private Integer pageNum = 1;


}







