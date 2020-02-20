package com.taotao.portal.pojo;

import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/19
 * @Time 22:51
 * 订单表，详情表，物流表三表的封装类
 */
@Getter
@Setter
public class OrderItemsShipping {
    private String orderId; //订单号

    private String payment; //支付金额

    private Integer paymentType; //支付类型

    private Integer status;//订单状态

    private Date createTime; //创建时间

    private Date closeTime;

    private Long userId;

    private String buyerNick;

    private List<TbOrderItem> orderItemList;

    private List<TbOrderShipping> orderShippingList;


}
