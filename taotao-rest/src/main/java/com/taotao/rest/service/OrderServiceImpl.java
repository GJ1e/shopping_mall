package com.taotao.rest.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.mapper.TbUserIntegralMapper;
import com.taotao.pojo.*;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.pojo.OrderInfo;
import com.taotao.rest.pojo.OrderItemsShipping;
import com.taotao.rest.pojo.SearchOrderResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 17:10
 * 生成订单服务
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    TbOrderMapper orderMapper;
    @Autowired
    TbOrderItemMapper orderItemMapper;
    @Autowired
    TbOrderShippingMapper orderShippingMapper;
    @Autowired
    TbUserIntegralMapper userIntegralMapper;
    @Autowired
    JedisClient jedisClient;

    @Value("${REDIS_ORDER_GEN_KEY}")
    private String REDIS_ORDER_GEN_KEY;
    @Value("${ORDER_ID_BEGIN}")
    private String ORDER_ID_BEGIN;
    @Value("${REDIS_ORDER_DETAIL_GEN_KEY}")
    private String REDIS_ORDER_DETAIL_GEN_KEY;

    /**
     * 生成订单
     * @param orderInfo
     * @return
     */
    @Transactional
    @Override
    public TaotaoResult creatOrder(OrderInfo orderInfo) {
//        一、插入订单表
//        1、接收数据OrderInfo
//        2、生成订单号
        //去订单号
        String id = jedisClient.get(REDIS_ORDER_GEN_KEY);
        if (StringUtils.isBlank(id)){
            jedisClient.set(REDIS_ORDER_GEN_KEY,ORDER_ID_BEGIN);
        }
        Long orderId = jedisClient.incr(REDIS_ORDER_GEN_KEY);
//        3、补全字段
        orderInfo.setOrderId(orderId.toString());
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(2);
        Date date = new Date();
        orderInfo.setCreateTime(date);
        orderInfo.setUpdateTime(date);
//        4、插入订单表
        orderMapper.insert(orderInfo);
//        二、插入订单明细
//        2、补全字段
        List<TbOrderItem> orderItemList = orderInfo.getOrderItems();
        for (TbOrderItem orderItem:orderItemList) {
    //      1、生成订单明细id，使用redis的incr命令生成。
            Long detailId = jedisClient.incr(REDIS_ORDER_DETAIL_GEN_KEY);
            orderItem.setId(detailId.toString());
            orderItem.setOrderId(orderId.toString());
//        3、插入数据
            orderItemMapper.insert(orderItem);
        }
//        三、插入物流表
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
//        1、补全字段
        orderShipping.setOrderId(orderId.toString());
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
//        2、插入数据
        orderShippingMapper.insert(orderShipping);

        //四插入用户积分表
        TbUserIntegral userIntegral = orderInfo.getUserIntegral();
        userIntegral.setUsername(orderInfo.getBuyerNick());
        userIntegral.setUserId(orderInfo.getUserId());
        TbUserIntegral haveUserIntegral = userIntegralMapper.queryUserIntegral(orderInfo.getUserId());
        if (haveUserIntegral != null){  //若存在，只更新用户名和积分
            userIntegralMapper.updateIntegral(userIntegral);
        }else{  //不存在则插入新数据
            userIntegralMapper.insert(userIntegral);
        }
//        五、返回TaotaoResult包装订单号。
        return TaotaoResult.ok(orderId);

    }

    /**
     * 我的订单页面数据展示
     * @param username
     * @param page
     * @param rows
     * @return
     */
    @Override
    public SearchOrderResult getOrderByUsername(String username, int page, int rows) {
        PageHelper.startPage(page,rows);
        List<OrderItemsShipping> orderItemsShippingList = new ArrayList<>();
        TbOrderExample orderExample = new TbOrderExample();
        TbOrderExample.Criteria orderCriteria = orderExample.createCriteria();
        orderCriteria.andBuyerNickEqualTo(username);
        List<TbOrder> orderList = orderMapper.selectByExample(orderExample);
        PageInfo<TbOrder> pageInfo = new PageInfo<>(orderList);
        //写入封装数据
        for (TbOrder order:orderList) {
            OrderItemsShipping orderItemsShipping = new OrderItemsShipping();
            orderItemsShipping.setOrderId(order.getOrderId());
            orderItemsShipping.setPayment(order.getPayment());
            orderItemsShipping.setPaymentType(order.getPaymentType());
            orderItemsShipping.setStatus(order.getStatus());
            orderItemsShipping.setCreateTime(order.getCreateTime());
            orderItemsShipping.setCloseTime(order.getCloseTime());
            orderItemsShipping.setUserId(order.getUserId());
            orderItemsShipping.setBuyerNick(order.getBuyerNick());
            orderItemsShipping.setShippingCode(order.getShippingCode());
            orderItemsShipping.setShippingName(order.getShippingName());

            //查询订单商品表
            String orderId = orderItemsShipping.getOrderId();
            TbOrderItemExample orderItemExample = new TbOrderItemExample();
            TbOrderItemExample.Criteria orderItemCriteria = orderItemExample.createCriteria();
            orderItemCriteria.andOrderIdEqualTo(orderId);
            List<TbOrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
            //写入订单商品表封装数据
            orderItemsShipping.setOrderItemList(orderItemList);
            //查询物流表
            TbOrderShippingExample orderShippingExample = new TbOrderShippingExample();
            TbOrderShippingExample.Criteria orderShippingCriteria = orderShippingExample.createCriteria();
            orderShippingCriteria.andOrderIdEqualTo(orderId);
            List<TbOrderShipping> orderShippingList = orderShippingMapper.selectByExample(orderShippingExample);
            //封装物流表数据
            orderItemsShipping.setOrderShippingList(orderShippingList);
            orderItemsShippingList.add(orderItemsShipping);
        }
        SearchOrderResult searchOrderResult = new SearchOrderResult();
        searchOrderResult.setData(orderItemsShippingList);
        searchOrderResult.setRecordCount(pageInfo.getTotal());
        Long recordCount = searchOrderResult.getRecordCount();
        int pageCount = (int) (recordCount / rows);
        if (recordCount % rows > 0) {
            pageCount++;
        }
        searchOrderResult.setPageCount(pageCount);
        searchOrderResult.setCurPage(page);
        return searchOrderResult;
    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @Override
    public TaotaoResult deleteOrderByOrderId(String orderId) {
        //删除订单表中信息
        orderMapper.deleteByPrimaryKey(orderId);
        //删除订单商品表信息
        TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        orderItemMapper.deleteByExample(example);
        //删除物流表中的信息
        orderShippingMapper.deleteByPrimaryKey(orderId);

        return TaotaoResult.ok();
    }

    /**
     * 根据订单号查询订单商品详情
     * @param orderId
     * @return
     */
    @Override
    public List<TbOrderItem> getOrderItemByOrderId(String orderId) {
        TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        List<TbOrderItem> list = orderItemMapper.selectByExample(example);
        return list;
    }

    @Override
    public TaotaoResult receiveOrderItem(String orderId) {
        TbOrder order = orderMapper.selectByPrimaryKey(orderId);
        //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        order.setStatus(5);
        order.setEndTime(new Date());
        order.setUpdateTime(new Date());
        orderMapper.updateByPrimaryKey(order);
        return TaotaoResult.ok();
    }
}
