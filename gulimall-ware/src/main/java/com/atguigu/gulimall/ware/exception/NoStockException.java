package com.atguigu.gulimall.ware.exception;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/17 20:23
 */
public class NoStockException extends RuntimeException {
    public NoStockException(Long skuId) {
        super("商品id：" + skuId + "没有足够的库存了");
    }
}
