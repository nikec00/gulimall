package com.atguigu.gulimall.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/21 21:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistVo {

    @NotEmpty(message = "用户名必须提交")
    @Length(min = 6 ,max = 18,message = "长度必须为6-18个字符")
    private String userName;

    @NotEmpty(message = "密码必须提交")
    @Length(min = 6 ,max = 18,message = "长度必须为6-18个字符")
    private String password;

    @NotEmpty(message = "手机号必须提交")
    @Pattern(regexp = "^[0]([3-9])[0-9]{9}$",message = "手机号格式非法")
    private String phone;

    @NotEmpty(message = "验证码必须提交")
    @Length(min = 6,max = 6)
    private String code ;
}
