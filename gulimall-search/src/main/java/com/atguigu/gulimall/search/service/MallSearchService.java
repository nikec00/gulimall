package com.atguigu.gulimall.search.service;

import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/6 18:55
 */
public interface MallSearchService {

    /**
     * @param searchParam 检索的所有参数
     * @return 返回检索的结果
     */
    SearchResult search(SearchParam searchParam);
}
