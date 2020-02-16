package com.taotao.portal.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 10:50
 * 购物车商品封装类
 */
@Getter
@Setter
public class CartItem {
    private Long id;
    private String title;
    private Long price;
    private Integer num;
    private String image;
}
