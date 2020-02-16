package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 18:36
 * 订单处理Service
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Override
    public String creatOrder(OrderInfo orderInfo) {
        //把OrderInfo转换成json
        String json = JsonUtils.objectToJson(orderInfo);
        //提交订单数据
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_URL+ORDER_CREATE_URL,json);
        //转换成Java对象
        TaotaoResult taotaoResult = TaotaoResult.format(jsonResult);
        //取订单号
        String orderId = taotaoResult.getData().toString();
        return orderId;
    }
}
