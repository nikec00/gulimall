package com.atguigu.gulimall.order.vo;

import com.atguigu.gulimall.order.entity.OrderEntity;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/11 22:39
 */
public class SubmitOrderResponseVo {
    private OrderEntity order;

    /** 错误状态码 **/
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
