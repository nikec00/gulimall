package com.atguigu.gulimall.ware.vo;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/20 11:55
 */
public class PurchaseItemDoneVo {

    private Long itemId;

    private Integer status;

    private String reason;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
