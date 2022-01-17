package com.atguigu.gulimall.ware.vo;

import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/17 20:00
 */
public class WareSkuLockVo {
    private String orderSn;

    private List<OrderItemVo> locks;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public List<OrderItemVo> getLocks() {
        return locks;
    }

    public void setLocks(List<OrderItemVo> locks) {
        this.locks = locks;
    }
}
