package com.taotao.rest.controller;


import com.taotao.pojo.TaotaoResult;
import com.taotao.rest.pojo.SearchResult;
import com.taotao.rest.service.SearchService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 11:25
 * 发布搜索服务
 */
@Controller
public class SearchController {
    @Autowired
    SearchService searchService;

    @RequestMapping("/search/q")
    @ResponseBody
    public TaotaoResult search(@RequestParam(defaultValue = "")String keyword,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "30") Integer rows){
        try {
            SearchResult searchResult = searchService.search(keyword,page,rows);
            return TaotaoResult.ok(searchResult);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping("/search/{cid}")
    @ResponseBody
    public TaotaoResult search(@PathVariable(value= "cid")Long cid,
                               @RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "30") Integer rows){
        try {
            SearchResult searchResult = searchService.searchItemByCid(cid,page,rows);
            return TaotaoResult.ok(searchResult);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

//    @RequestMapping("/search/{title}")
//    @ResponseBody
//    public TaotaoResult getSearch(@PathVariable String title){
//        List<TbItem> list = searchService.getTitleLike(title);
//        return TaotaoResult.ok(list);
//    }
}
