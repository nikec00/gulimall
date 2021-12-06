package com.atguigu.gulimall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/6 19:29
 */
@Data
public class AttrVo {

    private Long attrId;

    private String attrName;

    private List<String> attrValue;

}
