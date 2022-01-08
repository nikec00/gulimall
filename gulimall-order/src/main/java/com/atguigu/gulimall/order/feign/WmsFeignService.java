package com.atguigu.gulimall.order.feign;

import com.atguigu.gulimall.order.vo.SkuStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/8 22:50
 */
@FeignClient("gulimall-ware")
public interface WmsFeignService {

    @PostMapping("/ware/waresku/hasstock")
    public List<SkuStockVo> getSkusHasStock(@RequestBody List<Long> skuIds);
}
