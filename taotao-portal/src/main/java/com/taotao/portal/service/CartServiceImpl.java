package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 10:48
 * 购物车实现
 */
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    ItemService itemService;
    @Value("${COOKIE_EXPIRE}")
    private Integer COOKIE_EXPIRE;

    @Override
    public TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        //接收商品Id
        //从cookie中获取购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        boolean haveFlg = false;
        for (CartItem cartItem : itemList) {
            //如果商品存在，则商品数量加上参数中的商品数量
            if (cartItem.getId().longValue() == itemId){
                cartItem.setNum(cartItem.getNum()+num);
                haveFlg = true;
                break;
            }
        }
        //如果不存在，则调用rest服务，根据商品id获取商品信息
        if (!haveFlg){
            TbItem item = itemService.getItemById(itemId);
            //转换成cartItem
            CartItem cartItem = new CartItem();
            cartItem.setId(itemId);
            cartItem.setNum(num);
            cartItem.setPrice(item.getPrice());
            cartItem.setTitle(item.getTitle());
            if (StringUtils.isNotBlank(item.getImage())){
                String image = item.getImage();
                String[] strings = image.split(",");
                cartItem.setImage(strings[0]);
            }
            //把商品数据添加到购物车列表
            itemList.add(cartItem);
        }
        //把购物车列表写入到cookie
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(itemList),COOKIE_EXPIRE,true);
        //返回TaotaoResult
        return TaotaoResult.ok();
    }

    @Override
    public List<CartItem> getCartItems(HttpServletRequest request) {
        List<CartItem> itemList = getCartItemList(request);
        return itemList;
    }

    /**
     * 修改购物车商品数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取购物车列表
        List<CartItem> itemList = getCartItemList(request);
        //根据商品id查询
        for (CartItem cartItem: itemList) {
            if (cartItem.getId()==itemId){
                //更新数量
                cartItem.setNum(num);
                break;
            }
        }
        //写回cookie
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(itemList),COOKIE_EXPIRE,true);
        return TaotaoResult.ok();
    }

    /**
     * 删除购物车商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取 购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        //遍历列表，根据ID找到商品，然后删除
        for (CartItem cartItem : itemList) {
            if (cartItem.getId() == itemId){
                //删除商品
                itemList.remove(cartItem);
                break;
            }
        }
        //把商品列表写回cookie
        CookieUtils.setCookie(request,response,"TT_CART",JsonUtils.objectToJson(itemList),COOKIE_EXPIRE,true);

        return TaotaoResult.ok();
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @return
     */
    @Override
    public TaotaoResult deleteCartList(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request,response,"TT_CART");
        return TaotaoResult.ok();
    }

    /**
     * 取购物车商品列表
     * @param request
     * @return
     */
    private List<CartItem> getCartItemList(HttpServletRequest request){
        try{
            //从cookie中取商品列表
            String json = CookieUtils.getCookieValue(request,"TT_CART",true);
            List<CartItem> list = JsonUtils.jsonToList(json,CartItem.class);
            return list==null? new ArrayList<CartItem>():list;
        }catch (Exception e){
            return new ArrayList<CartItem>();
        }
    }
}
