package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author nkc
 * @email 760144709@qq.com
 * @date 2021-11-02 23:17:20
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
