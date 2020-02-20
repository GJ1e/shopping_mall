package com.taotao.rest.controller;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/11
 * @Time 16:47
 * 内容管理Controller
 */
@Controller
public class ContentController {
    @Autowired
    ContentService contentService;

    @RequestMapping("/content/{cid}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long cid){
        try {
            List<TbContent> contentList = contentService.getContentList(cid);
            return TaotaoResult.ok(contentList);
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/sync/content/{cid}")
    @ResponseBody
    public TaotaoResult syncConten(@PathVariable Long cid){
        try{
            TaotaoResult result = contentService.syncContent(cid);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getIndexData(Long categoryId, Integer page, Integer rows){
        EasyUIDataGridResult result = contentService.getIndexData(categoryId, page, rows);
        return result;
    }
}
