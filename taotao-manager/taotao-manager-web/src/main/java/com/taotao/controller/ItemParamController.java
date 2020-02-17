package com.taotao.controller;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    /**
     * 按商品类别查询商品规格参数模板
     * @param cid
     * @return
     */
    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResult getItemCatByCid(@PathVariable Long cid){
        TaotaoResult result = itemParamService.getItemParamByCid(cid);
        return result;
    }

    /**
     * 按类别添加商品规格参数模板
     * @param cid
     * @param paramData
     * @return
     */
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData){
        TaotaoResult result = itemParamService.insertItemParam(cid, paramData);
        return result;
    }

    /**
     * 规格参数分页查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(Integer page, Integer rows){
        EasyUIDataGridResult result = itemParamService.getItemParamList(page,rows);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItemParam(@RequestParam("ids") Long[] ids){
        for (int i = 0; i < ids.length; i++) {
            itemParamService.deleteItemParam(ids[i]);
        }
        return TaotaoResult.ok();
    }
}
