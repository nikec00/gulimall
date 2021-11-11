package com.atguigu.gulimall.product.vo;


/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/11 21:34
 */
public class AttrRespVo extends AttrVO {

    private String catelogName;
    private String groupName;

    public String getCatelogName() {
        return catelogName;
    }

    public void setCatelogName(String catelogName) {
        this.catelogName = catelogName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
