package com.atguigu.gulimall.order.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/17 20:00
 */
public class WareSkuLockVo implements Serializable {
    private static final long serialVersionUID = -4960324676802180498L;
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
