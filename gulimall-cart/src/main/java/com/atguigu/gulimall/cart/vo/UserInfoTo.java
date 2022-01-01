package com.atguigu.gulimall.cart.vo;

import lombok.Data;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/1 16:18
 */
@Data
public class UserInfoTo {

    private Long userId;

    private String userKey;

    private Boolean tempUser = false;
}
