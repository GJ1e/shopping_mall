package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 15:39
 * 页面跳转的Controller
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }


}
