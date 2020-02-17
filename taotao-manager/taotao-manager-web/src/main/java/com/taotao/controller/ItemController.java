package com.taotao.controller;

import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 11:04
 * 商品查询
 */
@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    /**
     * 根据商品Id查询商品
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem item = itemService.getItemById(itemId);
        return item;
    }
    /**
     * 根据商品Id查询商品描述
     * @param itemId
     * @return
     */
    @RequestMapping("/rest/item/query/item/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDescById(@PathVariable Long itemId){
        TaotaoResult result = itemService.getItemDescById(itemId);
        return result;
    }
    /**
     * 根据商品Id查询商品规格参数
     * @param itemId
     * @return
     */
    @RequestMapping("/rest/item/param/item/query/{itemId}")
    @ResponseBody
    public TaotaoResult getItemParamById(@PathVariable Long itemId){
        TaotaoResult result = itemService.getItemParamById(itemId);
        return result;
    }



    /**
     * 商品信息后台系统分页查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows){
        EasyUIDataGridResult result = itemService.getItemList(page,rows);
        return result;
    }

    /**
     * 后台添加商品
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping(value = "/item/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult creatItem(TbItem item,String desc, String itemParams){
        TaotaoResult result = itemService.createItem(item, desc, itemParams);
        return result;
    }

    /**
     * 后台更新商品
     * @param item
     * @param desc
     * @param itemParams
     * @return
     */
    @RequestMapping(value = "/rest/item/update", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateItem(TbItem item,String desc, String itemParams){
        TaotaoResult result = itemService.updateItem(item, desc, itemParams);
        return result;
    }

    /**
     * 批量删除商品
     * @param ids
     * @return
     */
    @RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult deleteItem(@RequestParam("ids") Long[] ids){

        for (int i = 0; i < ids.length; i++) {

            itemService.deleteItem(ids[i]);
        }
        return TaotaoResult.ok();
    }

    @RequestMapping(value = "/rest/item/instock", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateItemStock(@RequestParam("ids") Long[] ids){
        for (int i = 0; i < ids.length; i++) {
            itemService.updateItemStock(ids[i]);
        }
        return TaotaoResult.ok();
    }

}
