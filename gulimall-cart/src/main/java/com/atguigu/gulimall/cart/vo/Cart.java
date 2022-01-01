package com.atguigu.gulimall.cart.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description： 整个购物车
 *  需要计算属性，必须重写get方法。保证每次获取属性都会进行计算
 * @Author: nkc
 * @Date: 2022/1/1 15:36
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = 6621402635846243630L;

    private List<CartItem> items;

    private Integer countNum;//商品数量

    private Integer countType;//商品类型数量

    private BigDecimal totalAmount; //商品总价

    private BigDecimal reduce = new BigDecimal("0.00"); //减免价格

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getCountNum() {
        int count = 0;
        if (this.items != null && this.items.size() > 0) {
            for (CartItem item : this.items) {
                count += item.getCount();
            }
        }
        return count;
    }

    public Integer getCountType() {
        int count = 0;
        if (this.items != null && this.items.size() > 0) {
            for (CartItem item : this.items) {
                count += 1;
            }
        }
        return count;
    }

    public BigDecimal getTotalAmount() {
        BigDecimal decimal = new BigDecimal("0");
        //1.计算购物项总价
        if (this.items != null && this.items.size() > 0) {
            for (CartItem item : this.items) {
                BigDecimal totalPrice = item.getTotalPrice();
                decimal = decimal.add(totalPrice);
            }
        }
        //2.减去优惠总价
        BigDecimal subtract = decimal.subtract(getReduce());
        return subtract;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}
