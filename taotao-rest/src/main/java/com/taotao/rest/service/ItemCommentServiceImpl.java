package com.taotao.rest.service;

import com.taotao.mapper.TbItemCommentMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/21
 * @Time 20:39
 */
@Service
public class ItemCommentServiceImpl implements ItemCommentService{
    @Autowired
    TbItemCommentMapper itemCommentMapper;

    /**
     * 新增商品评论
     * @param itemComment
     * @return
     */
    @Override
    public TaotaoResult insertItemComment(TbItemComment itemComment) {
        itemCommentMapper.insert(itemComment);
        return TaotaoResult.ok();
    }

    @Override
    public List<TbItemComment> getItemCommentList(Long itemId) {
        List<TbItemComment> list = itemCommentMapper.selectByItemId(itemId);
        return list;
    }
}
