package com.taotao.controller;

import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/10
 * @Time 16:15
 * 内容分类管理Controller
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    ContentCategoryService contentCategoryService;

    /**
     * 内容分类查询
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult creatNode(Long parentId, String name){
        TaotaoResult result = contentCategoryService.insertCategory(parentId,name);
        return result;
    }
}
