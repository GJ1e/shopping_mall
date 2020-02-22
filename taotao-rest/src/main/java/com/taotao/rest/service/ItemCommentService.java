package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemComment;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/21
 * @Time 20:36
 */
public interface ItemCommentService {
    //新增商品评论
    TaotaoResult insertItemComment(TbItemComment itemComment);
    //查询商品评论
    List<TbItemComment> getItemCommentList(Long itemId);
}
