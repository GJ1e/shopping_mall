package com.taotao.portal.controller;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemComment;
import com.taotao.portal.service.ItemCommentService;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 12:16
 * 展示商品详情页面Controller
 */
@Controller
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemCommentService itemCommentService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model){
        TbItem item = itemService.getItemById(itemId);
        List<TbItemComment> commentList =  itemCommentService.getItemCommentList(itemId);
        model.addAttribute("commentList",commentList);
        model.addAttribute("item",item);
        return "item";
    }
    @RequestMapping(value = "/item/desc/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDescById(@PathVariable Long itemId){
        String desc = itemService.getItemDescById(itemId);
        return desc;
    }

    @RequestMapping(value = "/item/param/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParamById(@PathVariable Long itemId){
        String paramHtml = itemService.getItemParamById(itemId);
        return paramHtml;
    }

}
