package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author nkc
 * @email 760144709@qq.com
 * @date 2021-11-02 23:08:19
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
