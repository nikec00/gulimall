package com.atguigu.gulimall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/17 18:00
 */
@Data
public class SpuItemAttrGroupVo {

    private SkuItemVo groupName;

    private List<SpuBaseAttrVo> attrs;
}
