package com.atguigu.gulimall.order.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description： 订单确认页需要用的数据展示
 * @Author: nkc
 * @Date: 2022/1/7 23:42
 */
public class OrderConfirmVo implements Serializable {

    private static final long serialVersionUID = 6803122706919067768L;
    //收货地址：ums_member_receive_address表
    @Setter
    @Getter
    private List<MemberAddressVo> address;


    // 所有选中的购物项
    @Setter
    @Getter
    private List<OrderItemVo> items;

    // 发票信息

    // 优惠券信息
    @Setter
    @Getter
    private Integer integration;

    private Map<Long, Boolean> stocks;


    //订单总额
//    private BigDecimal total;

    //应付价格
//    private BigDecimal payPrice;

    @Setter
    @Getter
    private String orderToken;

    public Integer getCount() {
        Integer i = 0;
        if (items != null) {
            for (OrderItemVo item : items) {
                i += item.getCount();
            }
        }
        return i;
    }

    public List<MemberAddressVo> getAddress() {
        return address;
    }

    public void setAddress(List<MemberAddressVo> address) {
        this.address = address;
    }

    public List<OrderItemVo> getItems() {
        return items;
    }

    public void setItems(List<OrderItemVo> items) {
        this.items = items;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    // 总额
    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal("0");
        if (items != null) {
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum = sum.add(multiply);
            }
        }

        return sum;
    }

    //应付
    public BigDecimal getPayPrice() {
        return getTotal();
    }

    public Map<Long, Boolean> getStocks() {
        return stocks;
    }

    public void setStocks(Map<Long, Boolean> stocks) {
        this.stocks = stocks;
    }
}
