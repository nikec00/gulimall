package com.atguigu.gulimall.member.vo;

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
 * @Date: 2021/12/27 20:00
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegistVo {

    private String userName;

    private String password;

    private String phone;
}
