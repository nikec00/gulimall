package com.atguigu.gulimall.order.feign;

import com.atguigu.common.utils.R;
import com.atguigu.gulimall.order.vo.SkuStockVo;
import com.atguigu.gulimall.order.vo.WareSkuLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * 查询运费信息
     * @param addrId
     * @return
     */
    @GetMapping("/ware/wareinfo/fare")
    public R getFare(@RequestParam("addrId") Long addrId);

    /**
     * 为某个订单锁定库存
     * @param vo
     * @return
     */
    @PostMapping("/ware/waresku/lock/order")
    public R orderLockStock(@RequestBody WareSkuLockVo vo);

}
