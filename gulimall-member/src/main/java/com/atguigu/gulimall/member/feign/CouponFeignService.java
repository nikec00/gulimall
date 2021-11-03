package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/3 21:45
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @GetMapping("coupon/coupon/member/list")
    public R memberCoupons();
}
