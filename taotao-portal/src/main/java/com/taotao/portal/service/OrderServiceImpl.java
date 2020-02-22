package com.taotao.portal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbOrderItem;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.pojo.SearchOrderResult;
import com.taotao.utils.ExceptionUtil;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Value("${ORDER_QUERY_URL}")
    private String ORDER_QUERY_URL;

    @Value("${ORDER_DELETE_URL}")
    private String ORDER_DELETE_URL;

    @Value("${ORDER_QUERY_ITEM_URL}")
    private String ORDER_QUERY_ITEM_URL;

    @Value("${ORDER_RECEIVE_URL}")
    private String ORDER_RECEIVE_URL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

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

    @Override
    public Map<String,Object> getOrderList(String buyerNick, int page, int rows) {
        try {
            String jsonData = HttpClientUtil.doGet(REST_BASE_URL+ORDER_QUERY_URL+buyerNick+"/"+page+"/"+rows);
            if (jsonData == null){
                return new HashMap<String,Object>(0);
            }
            return MAPPER.readValue(jsonData,Map.class);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用订单分页查询服务，获取数据异常");
        }
        return new HashMap<String,Object>(0);

    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @Override
    public TaotaoResult deleteOrderByOrderId(String orderId) {
        try {
            String jsonData = HttpClientUtil.doGet(REST_BASE_URL+ORDER_DELETE_URL+orderId);
            return TaotaoResult.ok(jsonData);
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

    }

    /**
     * 根据订单号查询订单商品详情
     * @param orderId
     * @return
     */
    @Override
    public List<TbOrderItem> getOrderItemByOrderId(String orderId) {
        try {
            String jsonData = HttpClientUtil.doGet(REST_BASE_URL+ORDER_QUERY_ITEM_URL+orderId);
            List<TbOrderItem> list = JsonUtils.jsonToList(jsonData,TbOrderItem.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    @Override
    public TaotaoResult receiveOrderItem(String orderId) {
        try{
            String jsonData = HttpClientUtil.doGet(REST_BASE_URL+ORDER_RECEIVE_URL+orderId);
            return TaotaoResult.ok(jsonData);
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

}
