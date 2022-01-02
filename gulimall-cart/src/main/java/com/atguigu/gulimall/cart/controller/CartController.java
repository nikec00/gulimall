package com.atguigu.gulimall.cart.controller;

import com.atguigu.gulimall.cart.interceptor.CartInterceptor;
import com.atguigu.gulimall.cart.service.CartService;
import com.atguigu.gulimall.cart.vo.CartItem;
import com.atguigu.gulimall.cart.vo.UserInfoTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/1 16:09
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 当浏览器有一个cookie：user-key：标识用户身份，一个月后过期
     * 如果第一次使用jd的购物车功能，都会给一个临时的用户身份
     * 浏览器保存以后，每次访问都会带上这个cookie
     * <p>
     * 登录：session有
     * 没登陆：按照cookie里面带来的user-key来做
     * 第一次：如果没有临时用户，帮忙创建一个临时用户
     *
     * @return
     */
    @GetMapping("/cart.html")
    public String cartListPage() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        System.out.println("userInfoTo:" + userInfoTo);
        return "cartList";
    }

    /**
     * 添加商品加入购物车
     */
    @GetMapping("/addCartItem")
    public String addCartItem(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num, Model model) {
        CartItem cartItem = cartService.addCartItem(skuId, num);
        model.addAttribute("item", cartItem);
        return "success";
    }
}
