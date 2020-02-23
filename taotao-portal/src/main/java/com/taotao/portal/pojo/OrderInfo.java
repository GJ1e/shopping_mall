package com.taotao.portal.pojo;

import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbUserIntegral;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 16:58
 * 订单信息的封装类
 */
@Getter
@Setter
public class OrderInfo extends TbOrder {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;
    private TbUserIntegral userIntegral;
}
