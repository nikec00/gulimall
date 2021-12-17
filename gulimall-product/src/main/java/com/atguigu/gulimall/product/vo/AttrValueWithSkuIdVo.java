package com.atguigu.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/17 22:59
 */
@Data
@ToString
public class AttrValueWithSkuIdVo {
    private String attrValue;

    private String skuIds;
}
