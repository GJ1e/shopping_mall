package com.taotao.portal.controller;

import com.taotao.pojo.TbUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author GJ1e
 * @Create 2020/2/20
 * @Time 18:21
 * 用户管理Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/userInfo")
    public String userInfo(Model model, HttpServletRequest request){
        TbUser user = (TbUser)request.getAttribute("user");
        model.addAttribute("user",user);
        return "my-info";
    }
}
