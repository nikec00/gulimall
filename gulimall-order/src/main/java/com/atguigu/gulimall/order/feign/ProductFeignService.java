package com.atguigu.gulimall.order.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/12 22:13
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {
    @GetMapping("/product/spuinfo/skuId/{skuId}")
    public R getSpuInfoBySkuId(@PathVariable("skuId") Long skuId);
}
