package com.atguigu.gulimall.member.exception;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/27 20:13
 */
public class UserNameException extends RuntimeException {

    public UserNameException() {
        super("用户名存在");
    }
}
