package com.taotao.rest.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbOrderItem;
import com.taotao.rest.pojo.OrderInfo;
import com.taotao.rest.pojo.OrderItemsShipping;
import com.taotao.rest.pojo.SearchOrderResult;
import com.taotao.rest.service.OrderService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 订单创建
     * @param orderInfo
     * @return
     */
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

    /**
     * 订单表，订单商品详情表，订单物流表，三表分页查询
     * @param buyerNick
     * @param page
     * @param rows
     * @return
     */
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

    /**
     * 订单删除
     * @param orderId
     * @return
     */
    @RequestMapping("/order/delete/{orderId}")
    @ResponseBody
    public TaotaoResult deleteOrderByOrderId(@PathVariable("orderId")String orderId){
        TaotaoResult result = orderService.deleteOrderByOrderId(orderId);
        return result;
    }

    /**
     * 根据订单号查询订单商品详情
     * @param orderId
     * @return
     */
    @RequestMapping("/order/query/orderitem/{orderId}")
    @ResponseBody
    public List<TbOrderItem> getOrderItemByOrderId(@PathVariable("orderId")String orderId){
        List<TbOrderItem> list= orderService.getOrderItemByOrderId(orderId);
        return list;
    }

    @RequestMapping("/order/receive/orderitem/{orderId}")
    @ResponseBody
    public TaotaoResult receiveOrderItem(@PathVariable("orderId") String orderId){
        try{
            TaotaoResult result = orderService.receiveOrderItem(orderId);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }


}
