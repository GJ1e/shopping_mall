package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 10:47
 * 购物车
 */
public interface CartService {
    //添加商品到购物车
    TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
    //获取购物车商品列表
    List<CartItem> getCartItems(HttpServletRequest request);
    //修改购物车商品数量
    TaotaoResult updateCartItem(long itemId,Integer num, HttpServletRequest request, HttpServletResponse response);
    //删除购物车商品
    TaotaoResult deleteCartItem(long itemId,HttpServletRequest request, HttpServletResponse response);
    //清空购物车
    TaotaoResult deleteCartList(HttpServletRequest request, HttpServletResponse response);
}
