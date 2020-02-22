package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbOrderItem;
import com.taotao.rest.pojo.OrderInfo;
import com.taotao.rest.pojo.OrderItemsShipping;
import com.taotao.rest.pojo.SearchOrderResult;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 17:09
 */
public interface OrderService {
    //创建订单
    TaotaoResult creatOrder(OrderInfo orderInfo);
    //根据用户名分页查询订单
    SearchOrderResult getOrderByUsername(String username, int page, int rows);
    //订单删除
    TaotaoResult deleteOrderByOrderId(String orderId);
    //根据订单号查询订单商品
    List<TbOrderItem> getOrderItemByOrderId(String orderId);
    //用户确认收货
    TaotaoResult receiveOrderItem(String orderId);

}
