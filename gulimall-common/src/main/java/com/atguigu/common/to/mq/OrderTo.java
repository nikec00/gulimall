package com.atguigu.common.to.mq;

import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description��
 * @Author: nkc
 * @Date: 2022/1/27 10:06
 */
public class OrderTo {
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * member_id
     */
    private Long memberId;
    /**
     * ������
     */
    private String orderSn;
    /**
     * ʹ�õ��Ż�ȯ
     */
    private Long couponId;
    /**
     * create_time
     */
    private Date createTime;
    /**
     * �û���
     */
    private String memberUsername;
    /**
     * �����ܶ�
     */
    private BigDecimal totalAmount;
    /**
     * Ӧ���ܶ�
     */
    private BigDecimal payAmount;
    /**
     * �˷ѽ��
     */
    private BigDecimal freightAmount;
    /**
     * �����Ż��������ۡ����������ݼۣ�
     */
    private BigDecimal promotionAmount;
    /**
     * ���ֵֿ۽��
     */
    private BigDecimal integrationAmount;
    /**
     * �Ż�ȯ�ֿ۽��
     */
    private BigDecimal couponAmount;
    /**
     * ��̨��������ʹ�õ��ۿ۽��
     */
    private BigDecimal discountAmount;
    /**
     * ֧����ʽ��1->֧������2->΢�ţ�3->������ 4->���������
     */
    private Integer payType;
    /**
     * ������Դ[0->PC������1->app����]
     */
    private Integer sourceType;
    /**
     * ����״̬��0->�����1->��������2->�ѷ�����3->����ɣ�4->�ѹرգ�5->��Ч������
     */
    private Integer status;
    /**
     * ������˾(���ͷ�ʽ)
     */
    private String deliveryCompany;
    /**
     * ��������
     */
    private String deliverySn;
    /**
     * �Զ�ȷ��ʱ�䣨�죩
     */
    private Integer autoConfirmDay;
    /**
     * ���Ի�õĻ���
     */
    private Integer integration;
    /**
     * ���Ի�õĳɳ�ֵ
     */
    private Integer growth;
    /**
     * ��Ʊ����[0->������Ʊ��1->���ӷ�Ʊ��2->ֽ�ʷ�Ʊ]
     */
    private Integer billType;
    /**
     * ��Ʊ̧ͷ
     */
    private String billHeader;
    /**
     * ��Ʊ����
     */
    private String billContent;
    /**
     * ��Ʊ�˵绰
     */
    private String billReceiverPhone;
    /**
     * ��Ʊ������
     */
    private String billReceiverEmail;
    /**
     * �ջ�������
     */
    private String receiverName;
    /**
     * �ջ��˵绰
     */
    private String receiverPhone;
    /**
     * �ջ����ʱ�
     */
    private String receiverPostCode;
    /**
     * ʡ��/ֱϽ��
     */
    private String receiverProvince;
    /**
     * ����
     */
    private String receiverCity;
    /**
     * ��
     */
    private String receiverRegion;
    /**
     * ��ϸ��ַ
     */
    private String receiverDetailAddress;
    /**
     * ������ע
     */
    private String note;
    /**
     * ȷ���ջ�״̬[0->δȷ�ϣ�1->��ȷ��]
     */
    private Integer confirmStatus;
    /**
     * ɾ��״̬��0->δɾ����1->��ɾ����
     */
    private Integer deleteStatus;
    /**
     * �µ�ʱʹ�õĻ���
     */
    private Integer useIntegration;
    /**
     * ֧��ʱ��
     */
    private Date paymentTime;
    /**
     * ����ʱ��
     */
    private Date deliveryTime;
    /**
     * ȷ���ջ�ʱ��
     */
    private Date receiveTime;
    /**
     * ����ʱ��
     */
    private Date commentTime;
    /**
     * �޸�ʱ��
     */
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public BigDecimal getIntegrationAmount() {
        return integrationAmount;
    }

    public void setIntegrationAmount(BigDecimal integrationAmount) {
        this.integrationAmount = integrationAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }

    public Integer getAutoConfirmDay() {
        return autoConfirmDay;
    }

    public void setAutoConfirmDay(Integer autoConfirmDay) {
        this.autoConfirmDay = autoConfirmDay;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }

    public String getBillHeader() {
        return billHeader;
    }

    public void setBillHeader(String billHeader) {
        this.billHeader = billHeader;
    }

    public String getBillContent() {
        return billContent;
    }

    public void setBillContent(String billContent) {
        this.billContent = billContent;
    }

    public String getBillReceiverPhone() {
        return billReceiverPhone;
    }

    public void setBillReceiverPhone(String billReceiverPhone) {
        this.billReceiverPhone = billReceiverPhone;
    }

    public String getBillReceiverEmail() {
        return billReceiverEmail;
    }

    public void setBillReceiverEmail(String billReceiverEmail) {
        this.billReceiverEmail = billReceiverEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getUseIntegration() {
        return useIntegration;
    }

    public void setUseIntegration(Integer useIntegration) {
        this.useIntegration = useIntegration;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
