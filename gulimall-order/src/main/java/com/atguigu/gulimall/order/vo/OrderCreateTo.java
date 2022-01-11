package com.atguigu.gulimall.order.vo;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/11 23:06
 */
public class OrderCreateTo {
    // 订单
    private OrderEntity order;

    // 订单项
    private List<OrderItemEntity> orderItems;

    // 订单应付的价格
    private BigDecimal payPrice;

    //运费
    private BigDecimal fare;

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }
}
