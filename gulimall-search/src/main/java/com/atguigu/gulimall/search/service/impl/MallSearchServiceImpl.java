package com.atguigu.gulimall.search.service.impl;

import com.atguigu.gulimall.search.service.MallSearchService;
import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;
import org.springframework.stereotype.Service;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/6 18:56
 */
@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Override
    public SearchResult search(SearchParam searchParam) {
        //1.动态构建出查询所需要的dsl语句
        return null;
    }
}
