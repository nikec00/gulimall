package com.atguigu.gulimall.ware.vo;

import java.math.BigDecimal;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/10 21:46
 */
public class FareVo {
    private MemberAddressVo address;

    private BigDecimal fare;

    public MemberAddressVo getAddress() {
        return address;
    }

    public void setAddress(MemberAddressVo address) {
        this.address = address;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }
}
