package com.taotao.portal.controller;

import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        //传递给页面
        model.addAttribute("ad1",json);
        return "index";
    }

    @RequestMapping("/test")
    public String testPic(){
        return "test";
    }
}
