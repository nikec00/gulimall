package com.atguigu.gulimall.ware.vo;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/17 20:04
 */
public class LockStockResult {

    private Long skuId;

    private Integer num;

    private Boolean locked;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
