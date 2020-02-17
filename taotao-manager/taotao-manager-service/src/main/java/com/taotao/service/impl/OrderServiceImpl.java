package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderExample;
import com.taotao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
