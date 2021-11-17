package com.atguigu.gulimall.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/17 15:49
 */
@Data
public class BrandVo implements Serializable {

    @TableId
    private Long brandId;

    private String brandName;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
