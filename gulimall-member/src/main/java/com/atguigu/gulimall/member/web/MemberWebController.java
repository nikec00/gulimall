package com.atguigu.gulimall.member.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/28 15:23
 */
@Controller
public class MemberWebController {

    @GetMapping("/memberOrder.html")
    public String memberOrderPage() {

        //查出当前登录的用户的所有订单列表数据
        return "orderList";
    }
}
