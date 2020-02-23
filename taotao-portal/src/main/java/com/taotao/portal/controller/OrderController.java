package com.taotao.portal.controller;

import com.taotao.pojo.*;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.OrderInfo;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.ItemCommentService;
import com.taotao.portal.service.OrderService;
import com.taotao.portal.service.UserIntegralService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author GJ1e
 * @Create 2020/2/16
 * @Time 17:58
 * 订单系统Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    ItemCommentService itemCommentService;
    @Autowired
    UserIntegralService userIntegralService;

    @RequestMapping("/myOrder")
    public String showMyOrder(Model model, @RequestParam(value = "page",defaultValue = "1") Integer page,
                              @RequestParam(value = "rows",defaultValue = "6") Integer rows, HttpServletRequest request) {
        TbUser user = (TbUser)request.getAttribute("user");
        String buyerNick = user.getUsername();
        Map<String,Object> map = orderService.getOrderList(buyerNick,page,rows);
        model.addAttribute("orders",map);
        model.addAttribute("totalPages",map.get("pageCount"));
        model.addAttribute("page",map.get("curPage"));
        return "my-orders";
    }

    /**
     * 购物车页面数据
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/order-cart")
    public String showOrderCat(Model model, HttpServletRequest request) {
        //取购物车列表
        List<CartItem> list = cartService.getCartItems(request);
        //获得用户信息
        TbUser user = (TbUser) request.getAttribute("user");
        TbUserIntegral userIntegral = userIntegralService.queryUserIntegral(user.getId());
        //把购物车数据传递给jsp
        model.addAttribute("cartList", list);
        model.addAttribute("userIntegral",userIntegral);
        return "order-cart";
    }

    /**
     * 生成订单的方法，并跳转到下单成功页面
     * @param orderInfo
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String creatOrder(OrderInfo orderInfo, Model model, HttpServletRequest request, HttpServletResponse response) {
        //取用户信息
        TbUser user = (TbUser) request.getAttribute("user");
        //补全orderInfo的属性
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        //调用服务
        String orderId = orderService.creatOrder(orderInfo);
        //把订单号传递给页面
        model.addAttribute("orderId", orderId);
        model.addAttribute("payment", orderInfo.getPayment());
        DateTime dateTime = new DateTime();
        dateTime = dateTime.plusDays(3);
        model.addAttribute("date", dateTime.toString("yyyy-MM-dd"));
        //清空购物车
        cartService.deleteCartList(request,response);
        //返回逻辑视图
        return "success";
    }

    /**
     * 根据订单号删除订单
     * @param orderId
     * @return
     */
    @RequestMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable("orderId")String orderId){
        TaotaoResult result = orderService.deleteOrderByOrderId(orderId);
        return "redirect:/order/myOrder.html";
    }

    /**
     * 根据订单号查询订单商品详情
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping("/query/orderItem/{orderId}")
    public String getOrderItemByOrderId(@PathVariable("orderId") String orderId, Model model){
        List<TbOrderItem> orderItemList = orderService.getOrderItemByOrderId(orderId);
        model.addAttribute("orderItemList",orderItemList);
        return "my-order-comment";
    }

    /**
     * 商品评价
     * @param itemId
     * @param request
     * @return
     */
    @RequestMapping(value = "/add/comment/{itemId}", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertItemComment(@PathVariable("itemId")Long itemId, TbItemComment itemComment, HttpServletRequest request){
        TbUser user = (TbUser) request.getAttribute("user");
        itemComment.setItemId(itemId);
        itemComment.setUserId(user.getId());
        itemComment.setUsername(user.getUsername());

        TaotaoResult result = itemCommentService.insertItemComment(itemComment);
        return result;
    }

    @RequestMapping("/receive/orderitem/{orderId}")
    public String receiveOrderItem(@PathVariable("orderId")String orderId){
        TaotaoResult result = orderService.receiveOrderItem(orderId);
        return "redirect:/order/myOrder.html";
    }

}
