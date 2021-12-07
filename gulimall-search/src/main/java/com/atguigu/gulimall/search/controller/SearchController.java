package com.atguigu.gulimall.search.controller;

import com.atguigu.gulimall.search.service.MallSearchService;
import com.atguigu.gulimall.search.vo.SearchParam;
import com.atguigu.gulimall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/6 16:35
 */
@Controller
public class SearchController {

    @Autowired
    private MallSearchService mallSearchService;

    /**
     * 自动将页面提交过来的请求参数封装成指定对象
     *
     * @param searchParam
     * @return
     */
    @GetMapping("list.html")
    public String listPage(SearchParam searchParam, Model model) {
        //1.根据传递来的查询参数，去es中检索商品
        SearchResult res = mallSearchService.search(searchParam);
        model.addAttribute("result", res);
        return "list";
    }


}
