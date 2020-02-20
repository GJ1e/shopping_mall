package com.taotao.rest.service;

import com.taotao.pojo.EasyUIDataGridResult;
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
    //获取楼数据
    EasyUIDataGridResult getIndexData(Long categoryId, int page, int rows);

    //删除内容缓存
    TaotaoResult syncContent(Long cid);
}
