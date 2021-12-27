package com.atguigu.gulimall.auth.feign;

import com.atguigu.common.utils.R;
import com.atguigu.gulimall.auth.vo.UserLoginVo;
import com.atguigu.gulimall.auth.vo.UserRegistVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/27 20:42
 */
@FeignClient("gulimall-member")
public interface MemberFeignClient {

    @PostMapping("/member/member/regist")
    public R regist(@RequestBody UserRegistVo userRegistVo);

    @PostMapping("/member/member/login")
    public R login(@RequestBody UserLoginVo userLoginVo);
}
