package com.atguigu.gulimall.auth.vo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/29 22:52
 */
@Data
@ToString
public class MemberRespVo {


    private Long id;
    /**
     * 社交登录访问令牌
     */
    private String accessToken;
    /**
     * 社交登录过期时间
     */
    private Long expiresIn;
    /**
     * 社交登录唯一标识
     */
    private String socialUid;
    /**
     * 会员等级id
     */
    private Long levelId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String header;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 生日
     */
    private Date birth;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 职业
     */
    private String job;
    /**
     * 个性签名
     */
    private String sign;
    /**
     * 用户来源
     */
    private Integer sourceType;
    /**
     * 积分
     */
    private Integer integration;
    /**
     * 成长值
     */
    private Integer growth;
    /**
     * 启用状态
     */
    private Integer status;
    /**
     * 注册时间
     */
    private Date createTime;
}
