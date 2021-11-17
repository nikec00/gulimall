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
public class Skus {

    private List<Attr> attr;
    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private List<Images> images;
    private List<String> descar;
    private int fullCount;
    private BigDecimal discount;
    private int countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private int priceStatus;
    private List<MemberPrice> memberPrice;

    public void setAttr(List<Attr> attr) {
        this.attr = attr;
    }

    public List<Attr> getAttr() {
        return attr;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuSubtitle(String skuSubtitle) {
        this.skuSubtitle = skuSubtitle;
    }

    public String getSkuSubtitle() {
        return skuSubtitle;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setDescar(List<String> descar) {
        this.descar = descar;
    }

    public List<String> getDescar() {
        return descar;
    }

    public void setFullCount(int fullCount) {
        this.fullCount = fullCount;
    }

    public int getFullCount() {
        return fullCount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setCountStatus(int countStatus) {
        this.countStatus = countStatus;
    }

    public int getCountStatus() {
        return countStatus;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setReducePrice(BigDecimal reducePrice) {
        this.reducePrice = reducePrice;
    }

    public BigDecimal getReducePrice() {
        return reducePrice;
    }

    public void setPriceStatus(int priceStatus) {
        this.priceStatus = priceStatus;
    }

    public int getPriceStatus() {
        return priceStatus;
    }

    public void setMemberPrice(List<MemberPrice> memberPrice) {
        this.memberPrice = memberPrice;
    }

    public List<MemberPrice> getMemberPrice() {
        return memberPrice;
    }

}