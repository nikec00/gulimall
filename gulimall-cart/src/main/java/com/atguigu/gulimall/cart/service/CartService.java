package com.atguigu.gulimall.cart.service;

import com.atguigu.gulimall.cart.vo.Cart;
import com.atguigu.gulimall.cart.vo.CartItem;

import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/1 15:55
 */
public interface CartService {
    CartItem addCartItem(Long skuId, Integer num);

    CartItem getCartItem(Long skuId);

    Cart getCart();

    void clearCart(String useKey);

    void checkItem(Long skuId, Integer check);

    void changeItemCount(Long skuId, Integer num);

    void deleteItem(Long skuId);

    List<CartItem> getCurrentUserCartItems();
}
