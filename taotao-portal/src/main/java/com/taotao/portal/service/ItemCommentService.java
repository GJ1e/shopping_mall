package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemComment;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/21
 * @Time 22:25
 */
public interface ItemCommentService {
    //增加商品评论
    TaotaoResult insertItemComment(TbItemComment itemComment);

    List<TbItemComment> getItemCommentList(Long itemId);
}
