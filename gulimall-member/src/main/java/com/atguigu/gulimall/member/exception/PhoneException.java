package com.atguigu.gulimall.member.exception;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/27 20:13
 */
public class PhoneException extends RuntimeException{
    public PhoneException() {
        super("手机号已存在");
    }
}
