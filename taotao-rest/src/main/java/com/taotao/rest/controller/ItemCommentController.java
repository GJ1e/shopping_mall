package com.taotao.rest.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemComment;
import com.taotao.rest.service.ItemCommentService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/21
 * @Time 20:44
 * 商品评论Controller
 */
@Controller
public class ItemCommentController {
    @Autowired
    ItemCommentService itemCommentService;

    /**
     * 插入商品评论
     * @param itemComment
     * @return
     */
    @RequestMapping(value = "/comment/addcomment", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertItemComment(@RequestBody TbItemComment itemComment){
        try{
            TaotaoResult result = itemCommentService.insertItemComment(itemComment);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/comment/queryComment/{itemId}")
    @ResponseBody
    public List<TbItemComment> insertItemComment(@PathVariable("itemId") Long itemId){
        try{
            List<TbItemComment> list = itemCommentService.getItemCommentList(itemId);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
