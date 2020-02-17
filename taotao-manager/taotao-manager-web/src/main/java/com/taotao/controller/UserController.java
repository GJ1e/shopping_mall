package com.taotao.controller;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author GJ1e
 * @Create 2020/2/17
 * @Time 20:56
 * 后台用户管理Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getUserList(Integer page, Integer rows){
        EasyUIDataGridResult result = userService.getUserList(page,rows);
        return result;
    }
}
