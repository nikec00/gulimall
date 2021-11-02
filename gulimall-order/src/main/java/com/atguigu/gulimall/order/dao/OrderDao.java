package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author nkc
 * @email 760144709@qq.com
 * @date 2021-11-02 23:29:10
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
