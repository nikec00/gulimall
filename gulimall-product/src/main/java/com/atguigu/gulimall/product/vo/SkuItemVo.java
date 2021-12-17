package com.atguigu.gulimall.product.vo;

import com.atguigu.gulimall.product.entity.SkuImagesEntity;
import com.atguigu.gulimall.product.entity.SkuInfoEntity;
import com.atguigu.gulimall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/17 17:46
 */
@Data
public class SkuItemVo implements Serializable {
    private static final long serialVersionUID = 1249968435835877937L;

    private SkuInfoEntity info;

    private List<SkuImagesEntity> images;

    private List<SkuItemSaleAttrVo> saleAttr;

    private SpuInfoDescEntity desc;

    private List<SpuItemAttrGroupVo> groupAttrs;


}
