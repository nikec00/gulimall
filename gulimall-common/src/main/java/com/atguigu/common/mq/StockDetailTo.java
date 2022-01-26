package com.atguigu.common.mq;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @Description��
 * @Author: nkc
 * @Date: 2022/1/26 16:17
 */
public class StockDetailTo {
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * sku_id
     */
    private Long skuId;
    /**
     * sku_name
     */
    private String skuName;
    /**
     * �������
     */
    private Integer skuNum;
    /**
     * ������id
     */
    private Long taskId;
    /**
     * �ֿ�id
     */
    private Long wareId;
    /**
     * 1-������  2-�ѽ���  3-�ۼ�
     */
    private Integer lockStatus;

    public StockDetailTo() {
    }

    public StockDetailTo(Long id, Long skuId, String skuName, Integer skuNum, Long taskId, Long wareId, Integer lockStatus) {
        this.id = id;
        this.skuId = skuId;
        this.skuName = skuName;
        this.skuNum = skuNum;
        this.taskId = taskId;
        this.wareId = wareId;
        this.lockStatus = lockStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getSkuNum() {
        return skuNum;
    }

    public void setSkuNum(Integer skuNum) {
        this.skuNum = skuNum;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }
}
