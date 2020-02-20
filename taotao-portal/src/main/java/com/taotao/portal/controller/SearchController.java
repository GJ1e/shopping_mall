package com.taotao.portal.controller;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author GJ1e
 * @Create 2020/2/13
 * @Time 14:33
 * 商品搜索Controller
 */
@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q")String keyword,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "60") Integer rows,
                         Model model){
        SearchResult searchResult = searchService.search(keyword,page,rows);
        //传递参数给页面
        model.addAttribute("query",keyword);
        model.addAttribute("totalPages",searchResult.getPageCount());
        model.addAttribute("itemList",searchResult.getList());
        model.addAttribute("page",searchResult.getCurPage());

        //返回逻辑视图
        return "search";
    }
    @RequestMapping("/search/{cid}")
    public String search(@PathVariable("cid")Long cid,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "60") Integer rows,
                         Model model){
        SearchResult searchResult = searchService.searchItemByCid(cid,page,rows);
        //传递参数给页面
        model.addAttribute("query","");
        model.addAttribute("totalPages",searchResult.getPageCount());
        model.addAttribute("itemList",searchResult.getList());
        model.addAttribute("page",searchResult.getCurPage());

        //返回逻辑视图
        return "search";
    }
}
