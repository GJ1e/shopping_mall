package com.taotao.portal.service;

import com.taotao.portal.pojo.OrderInfo;

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
}
