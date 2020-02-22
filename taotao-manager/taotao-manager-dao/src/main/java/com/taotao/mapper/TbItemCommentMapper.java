package com.taotao.mapper;


import com.taotao.pojo.TbItemComment;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/21
 * @Time 19:56
 */
public interface TbItemCommentMapper {
    List<TbItemComment> selectByItemId(Long itemId);
    int insert(TbItemComment itemComment);
}
