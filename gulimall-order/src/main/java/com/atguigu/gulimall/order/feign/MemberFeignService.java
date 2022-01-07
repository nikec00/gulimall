package com.atguigu.gulimall.order.feign;

import com.atguigu.gulimall.order.vo.MemberAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/7 23:53
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 返回会员所有收货地址列表
     * @param memberId
     * @return
     */
    @GetMapping("/member/memberreceiveaddress/{memberId}/addresses")
    public List<MemberAddressVo> getAddress(@PathVariable("memberId") Long memberId);
}
