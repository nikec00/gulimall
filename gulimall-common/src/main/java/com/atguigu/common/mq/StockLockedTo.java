package com.atguigu.common.mq;

import java.util.List;

/**
 * @Description��
 * @Author: nkc
 * @Date: 2022/1/26 16:06
 */
public class StockLockedTo {

    /**
     * ��湤����id
     */
    private Long id;

    /**
     * ����id
     */
    private StockDetailTo detail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockDetailTo getDetailTo() {
        return detail;
    }

    public void setDetailTo(StockDetailTo detail) {
        this.detail = detail;
    }
}
