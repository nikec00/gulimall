package com.atguigu.common.vo;

import lombok.Data;

/**
 * @Description：社交登录得到access_token等信息
 * @Author: nkc
 * @Date: 2021/12/29 21:57
 */
@Data
public class SocialUser {
    private String access_token;
    private String remind_in;
    private long expires_in;
    private String uid;
    private String isRealName;
}
