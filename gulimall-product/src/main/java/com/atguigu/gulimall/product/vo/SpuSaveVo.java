/**
 * Copyright 2021 bejson.com
 */
package com.atguigu.gulimall.product.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2021-11-17 17:21:56
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SpuSaveVo {

    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private int publishStatus;
    private List<String> decript;
    private List<String> images;
    private Bounds bounds;
    private List<BaseAttrs> baseAttrs;
    private List<Skus> skus;

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuDescription(String spuDescription) {
        this.spuDescription = spuDescription;
    }

    public String getSpuDescription() {
        return spuDescription;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }

    public int getPublishStatus() {
        return publishStatus;
    }

    public void setDecript(List<String> decript) {
        this.decript = decript;
    }

    public List<String> getDecript() {
        return decript;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBaseAttrs(List<BaseAttrs> baseAttrs) {
        this.baseAttrs = baseAttrs;
    }

    public List<BaseAttrs> getBaseAttrs() {
        return baseAttrs;
    }

    public void setSkus(List<Skus> skus) {
        this.skus = skus;
    }

    public List<Skus> getSkus() {
        return skus;
    }

}