package com.atguigu.gulimall.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/7 23:47
 */
@Data
public class OrderItemVo {
    private Long skuId;

    private Boolean check;

    private String title;

    private String image;

    private List<String> skuAttr;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;
}
