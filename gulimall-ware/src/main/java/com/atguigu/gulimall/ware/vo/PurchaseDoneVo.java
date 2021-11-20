package com.atguigu.gulimall.ware.vo;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/20 11:54
 */
public class PurchaseDoneVo {
    @NotNull
    private Long id;

    private List<PurchaseItemDoneVo> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PurchaseItemDoneVo> getItems() {
        return items;
    }

    public void setItems(List<PurchaseItemDoneVo> items) {
        this.items = items;
    }


}
