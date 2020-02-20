package com.taotao.portal.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/20
 * @Time 10:35
 * 订单表，订单商品详情，物流表返回结果封装
 */
@Getter
@Setter
public class SearchOrderResult {
    private List<OrderItemsShipping> data;
    private Long recordCount;
    private int pageCount;
    private int curPage;
}
