package com.taotao.controller;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author GJ1e
 * @Create 2020/2/17
 * @Time 22:28
 * 订单管理Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getOrderList(Integer page,Integer rows){
        EasyUIDataGridResult result = orderService.getOrderList(page,rows);
        return result;
    }
}
