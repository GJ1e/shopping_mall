package com.taotao.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author GJ1e
 * @Create 2020/2/8
 * @Time 21:37
 * 商品规格参数管理Controller
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResult getItemCatByCid(@PathVariable Long cid){
        TaotaoResult result = itemParamService.getItemParamByCid(cid);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData){
        TaotaoResult result = itemParamService.insertItemParam(cid, paramData);
        return result;
    }
}
