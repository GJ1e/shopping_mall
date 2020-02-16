package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/11
 * @Time 16:39
 */
public interface ContentService {
    List<TbContent> getContentList(Long cid);

    //删除内容缓存
    TaotaoResult syncContent(Long cid);
}
