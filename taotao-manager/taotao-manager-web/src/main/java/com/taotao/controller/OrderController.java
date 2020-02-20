package com.taotao.controller;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 分页查询订单表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getOrderList(Integer page,Integer rows){
        EasyUIDataGridResult result = orderService.getOrderList(page,rows);
        return result;
    }

    /**
     * 删除订单信息
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteOrderByOrderId(@RequestParam("ids")String[] ids){
        for (int i = 0; i < ids.length; i++) {
            orderService.deleteOrderByOrderId(ids[i]);
        }
        return TaotaoResult.ok();
    }

    /**
     * 后台更新订单，发货操作
     * @param order
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateOrder(TbOrder order){
        TaotaoResult result = orderService.updateOrder(order);
        return result;
    }

    /**
     * 订单详情分页查询
     * @param orderId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/query/detail")
    @ResponseBody
    public EasyUIDataGridResult getOrderDetail(@RequestParam("orderId")String orderId, Integer page, Integer rows){
        EasyUIDataGridResult result = orderService.getOrderDetail(orderId,page,rows);
        return result;
    }
}
