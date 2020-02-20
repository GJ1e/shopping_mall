package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbOrder;

/**
 * @Author GJ1e
 * @Create 2020/2/17
 * @Time 22:23
 * 订单管理
 */
public interface OrderService {
    //订单分页查询
    EasyUIDataGridResult getOrderList(int page, int rows);

    //根据订单号删除订单
    TaotaoResult deleteOrderByOrderId(String orderId);

    //更新订单，发货
    TaotaoResult updateOrder(TbOrder order);

    //根据订单号查询订单中的商品详情
    EasyUIDataGridResult getOrderDetail(String orderId, int page, int rows);
}
