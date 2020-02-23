package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.pojo.*;
import com.taotao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/17
 * @Time 22:25
 * 订单管理
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    TbOrderMapper orderMapper;
    @Autowired
    TbOrderItemMapper orderItemMapper;
    @Autowired
    TbOrderShippingMapper orderShippingMapper;

    /**
     * 分页查询订单列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getOrderList(int page, int rows) {
        PageHelper.startPage(page, rows);
        TbOrderExample example = new TbOrderExample();
        List<TbOrder> list = orderMapper.selectByExample(example);
        PageInfo<TbOrder> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    /**
     * 根据订单号删除订单
     * @param orderId
     * @return
     */
    @Override
    public TaotaoResult deleteOrderByOrderId(String orderId) {
        //删除订单表
        orderMapper.deleteByPrimaryKey(orderId);
        //删除物流表
        orderShippingMapper.deleteByPrimaryKey(orderId);
        //删除订单商品表
        TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);
        orderItemMapper.deleteByExample(example);
        return TaotaoResult.ok();
    }

    /**
     * 更新订单，后台发货
     * @param order
     * @return
     */
    @Override
    public TaotaoResult updateOrder(TbOrder order) {
        //更新订单状态 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭'
        order.setStatus(4);
        //更新发货时间
        order.setConsignTime(new Date());
        //订单更新时间
        order.setUpdateTime(new Date());
        //订单创建时间
        order.setCreateTime(new Date());
        orderMapper.updateByPrimaryKey(order);
        return TaotaoResult.ok();
    }

    /**
     * 订单详情分页查询
     * @param orderId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getOrderDetail(String orderId, int page, int rows) {
        PageHelper.startPage(page,rows);
        TbOrderItemExample example = new TbOrderItemExample();
        List<TbOrderItem> orderItemList = new ArrayList<>();
        if (!orderId.equals(0)){
            TbOrderItemExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(orderId);
            orderItemList = orderItemMapper.selectByExample(example);
        }else{

            orderItemList = orderItemMapper.selectByExample(example);
        }
        PageInfo<TbOrderItem> pageInfo = new PageInfo<>(orderItemList);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(orderItemList);
        return result;
    }
}
