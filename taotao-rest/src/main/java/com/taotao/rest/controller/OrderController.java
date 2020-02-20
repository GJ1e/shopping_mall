package com.taotao.rest.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.rest.pojo.OrderInfo;
import com.taotao.rest.pojo.OrderItemsShipping;
import com.taotao.rest.pojo.SearchOrderResult;
import com.taotao.rest.service.OrderService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 17:40
 * 订单服务Controller
 */
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody OrderInfo orderInfo){
        try{
            TaotaoResult result = orderService.creatOrder(orderInfo);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping(value = "/order/query/{buyerNick}/{page}/{rows}")
    @ResponseBody
    public SearchOrderResult queryOrderByUserNameAndPage(@PathVariable("buyerNick") String buyerNick,
                              @PathVariable("page") Integer page, @PathVariable("rows") Integer rows) {
        try{
            SearchOrderResult result = orderService.getOrderByUsername(buyerNick,page,rows);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
