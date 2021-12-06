package com.atguigu.gulimall.search.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/6 19:27
 */
@Data
public class BrandVo implements Serializable {

    private static final long serialVersionUID = -3195389713745635787L;
    private Long brandId;

    private String brandName;

    private String brandImg;
}
