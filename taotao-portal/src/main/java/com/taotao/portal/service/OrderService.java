package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbOrderItem;
import com.taotao.portal.pojo.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 18:30
 */
public interface OrderService {
    //生成订单
    String creatOrder(OrderInfo orderInfo);
    //前端我的订单分页展示
    Map<String,Object> getOrderList(String buyerNick, int page, int rows);
    //删除订单
    TaotaoResult deleteOrderByOrderId(String orderId);
    //根据订单号查询订单商品详情
    List<TbOrderItem> getOrderItemByOrderId(String orderId);
    //用户确认收货
    TaotaoResult receiveOrderItem(String orderId);
}
