package com.atguigu.common.to;

import lombok.Data;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/24 16:04
 */
@Data
public class SkuHasStockVo {

    private Long skuId;

    private Boolean hasStock;
}
