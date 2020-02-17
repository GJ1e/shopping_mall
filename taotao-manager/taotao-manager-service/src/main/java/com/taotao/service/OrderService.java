package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;

/**
 * @Author GJ1e
 * @Create 2020/2/17
 * @Time 22:23
 * 订单管理
 */
public interface OrderService {
    //订单分页查询
    EasyUIDataGridResult getOrderList(int page, int rows);
}
