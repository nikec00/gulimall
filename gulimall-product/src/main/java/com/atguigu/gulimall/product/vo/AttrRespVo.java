package com.atguigu.gulimall.product.vo;


/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/11 21:34
 */
public class AttrRespVo extends AttrVo {

    private String catelogName;
    private String groupName;
    private Long[] catelogPath;

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

    public Long[] getCatelogPath() {
        return catelogPath;
    }

    public void setCatelogPath(Long[] catelogPath) {
        this.catelogPath = catelogPath;
    }
}
