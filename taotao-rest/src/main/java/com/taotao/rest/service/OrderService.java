package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.rest.pojo.OrderInfo;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 17:09
 */
public interface OrderService {
    TaotaoResult creatOrder(OrderInfo orderInfo);

}
