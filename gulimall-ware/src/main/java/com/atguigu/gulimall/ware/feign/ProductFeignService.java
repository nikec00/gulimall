package com.atguigu.gulimall.ware.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/20 12:58
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {

    @RequestMapping("/product/skuinfo/info/{skuId}")
    //@RequestMapping("/api/product/skuinfo/info/{skuId}") 给gulimall-gateway所在的机器发送请求
    public R info(@PathVariable("skuId") Long skuId);

}
