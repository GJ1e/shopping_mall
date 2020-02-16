package com.taotao.rest.controller;

import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author GJ1e
 * @Create 2020/2/9
 * @Time 20:11
 * 商品分类查询服务Controller
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    ItemCatService itemCatService;

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback) {
        ItemCatResult result = itemCatService.getItemCatList();
        //需要把result转换成字符串
        String json = JsonUtils.objectToJson(result);
        if (StringUtils.isBlank(callback)){
            return json;
        }
        //如果字符串不为空，需要支持jsonp调用
        //需要把result转换成字符串
        return callback+"("+json+");";
    }
}
