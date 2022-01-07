package com.atguigu.gulimall.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description： 订单确认页需要用的数据展示
 * @Author: nkc
 * @Date: 2022/1/7 23:42
 */
@Data
public class OrderConfirmVo implements Serializable {

    private static final long serialVersionUID = 6803122706919067768L;
    //收货地址：ums_member_receive_address表
    private List<MemberAddressVo> address;

    // 所有选中的购物项
    private List<OrderItemVo> items;

    // 发票信息

    // 优惠券信息
    private Integer integration;

    //订单总额
    private BigDecimal total;

    //应付价格
    private BigDecimal payPrice;


}
