package com.atguigu.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单项信息
 * 
 * @author nkc
 * @email 760144709@qq.com
 * @date 2021-11-02 23:29:10
 */
@Data
@TableName("oms_order_item")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * order_id
	 */
	private Long orderId;
	/**
	 * order_sn
	 */
	private String orderSn;
	/**
	 * spu_id
	 */
	private Long spuId;
	/**
	 * spu_name
	 */
	private String spuName;
	/**
	 * spu_pic
	 */
	private String spuPic;
	/**
	 * 品牌
	 */
	private String spuBrand;
	/**
	 * 商品分类id
	 */
	private Long categoryId;
	/**
	 * 商品sku编号
	 */
	private Long skuId;
	/**
	 * 商品sku名字
	 */
	private String skuName;
	/**
	 * 商品sku图片
	 */
	private String skuPic;
	/**
	 * 商品sku价格
	 */
	private BigDecimal skuPrice;
	/**
	 * 商品购买的数量
	 */
	private Integer skuQuantity;
	/**
	 * 商品销售属性组合（JSON）
	 */
	private String skuAttrsVals;
	/**
	 * 商品促销分解金额
	 */
	private BigDecimal promotionAmount;
	/**
	 * 优惠券优惠分解金额
	 */
	private BigDecimal couponAmount;
	/**
	 * 积分优惠分解金额
	 */
	private BigDecimal integrationAmount;
	/**
	 * 该商品经过优惠后的分解金额
	 */
	private BigDecimal realAmount;
	/**
	 * 赠送积分
	 */
	private Integer giftIntegration;
	/**
	 * 赠送成长值
	 */
	private Integer giftGrowth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public String getSpuName() {
		return spuName;
	}

	public void setSpuName(String spuName) {
		this.spuName = spuName;
	}

	public String getSpuPic() {
		return spuPic;
	}

	public void setSpuPic(String spuPic) {
		this.spuPic = spuPic;
	}

	public String getSpuBrand() {
		return spuBrand;
	}

	public void setSpuBrand(String spuBrand) {
		this.spuBrand = spuBrand;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public String getSkuPic() {
		return skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public BigDecimal getSkuPrice() {
		return skuPrice;
	}

	public void setSkuPrice(BigDecimal skuPrice) {
		this.skuPrice = skuPrice;
	}

	public Integer getSkuQuantity() {
		return skuQuantity;
	}

	public void setSkuQuantity(Integer skuQuantity) {
		this.skuQuantity = skuQuantity;
	}

	public String getSkuAttrsVals() {
		return skuAttrsVals;
	}

	public void setSkuAttrsVals(String skuAttrsVals) {
		this.skuAttrsVals = skuAttrsVals;
	}

	public BigDecimal getPromotionAmount() {
		return promotionAmount;
	}

	public void setPromotionAmount(BigDecimal promotionAmount) {
		this.promotionAmount = promotionAmount;
	}

	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getIntegrationAmount() {
		return integrationAmount;
	}

	public void setIntegrationAmount(BigDecimal integrationAmount) {
		this.integrationAmount = integrationAmount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public Integer getGiftIntegration() {
		return giftIntegration;
	}

	public void setGiftIntegration(Integer giftIntegration) {
		this.giftIntegration = giftIntegration;
	}

	public Integer getGiftGrowth() {
		return giftGrowth;
	}

	public void setGiftGrowth(Integer giftGrowth) {
		this.giftGrowth = giftGrowth;
	}
}
