package com.taotao.controller;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author GJ1e
 * @Create 2020/2/10
 * @Time 18:03
 * 内容管理Controller
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    @Autowired
    ContentService contentService;

    @RequestMapping("/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content){
        TaotaoResult result =contentService.insertContent(content);
        //调用taotao-rest服务同步缓存
//        HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());

        return result;
    }

    /**
     * 内容管理分页查询
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(@RequestParam(value = "categoryId") Long categoryId, Integer page, Integer rows){
        EasyUIDataGridResult result = contentService.getContentList(categoryId,page,rows);
        return result;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public TaotaoResult updateContent(TbContent content){
        TaotaoResult result = contentService.updateContent(content);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContent(@RequestParam("ids")Long[] ids){
        for (int i = 0; i < ids.length; i++) {
            contentService.deleteContent(ids[i]);
        }
        return TaotaoResult.ok();
    }
}
