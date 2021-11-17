package com.atguigu.common.to;

import java.math.BigDecimal;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/17 21:42
 */
public class SpuBoundTo {

    private Long spuId;

    private BigDecimal buyBounds;

    private int growBounds;

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }

    public BigDecimal getBuyBounds() {
        return buyBounds;
    }

    public void setBuyBounds(BigDecimal buyBounds) {
        this.buyBounds = buyBounds;
    }

    public int getGrowBounds() {
        return growBounds;
    }

    public void setGrowBounds(int growBounds) {
        this.growBounds = growBounds;
    }
}
