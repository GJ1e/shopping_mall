package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author GJ1e
 * @Create 2020/2/9
 * @Time 15:58
 * 首页访问Controller
 */
@Controller
public class IndexController {
    @Autowired
    ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        //取大广告位内容
        String json = contentService.getAD1List();
        String json1 = contentService.getIndexDate();
//        ModelAndView mv = new ModelAndView("index");
        //传递给页面
//        mv.addObject("ad1",json);
        model.addAttribute("ad1",json);
        model.addAttribute("indexDate",json1);
//        mv.addObject("indexDate",json1);
        return "index";
    }

    @RequestMapping("/{path}")
    public String testPic(@PathVariable String path){
        return path;
    }
}
