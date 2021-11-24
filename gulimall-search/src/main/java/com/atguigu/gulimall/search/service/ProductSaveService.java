package com.atguigu.gulimall.search.service;

import com.atguigu.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/24 16:50
 */
public interface ProductSaveService {
    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
