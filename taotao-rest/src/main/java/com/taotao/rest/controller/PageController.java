package com.taotao.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author GJ1e
 * @Create 2020/2/15
 * @Time 17:28
 * 界面展示Controller
 */
@Controller
public class PageController {
    /**
     * 用户登录
     * @return
     */
    @RequestMapping("/page/login")
    public String showLogin(String redirectURL, Model model){
        //把参数传给jsp
        model.addAttribute("redirect",redirectURL);
        return "login";
    }

    @RequestMapping("/page/register")
    public String showRegister(){
        return "register";
    }
}
