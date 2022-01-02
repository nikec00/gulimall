package com.atguigu.gulimall.cart.service;

import com.atguigu.gulimall.cart.vo.CartItem;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/1 15:55
 */
public interface CartService {
    CartItem addCartItem(Long skuId, Integer num);

    CartItem getCartItem(Long skuId);
}
