package com.atguigu.gulimall.order.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/7 23:27
 */
@Controller
public class OrderWebController {

    @GetMapping("/toTrade")
    public String toTrade() {
        return "confirm";
    }
}
