package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * @Author GJ1e
 * @Create 2020/2/10
 * @Time 17:56
 * 内容管理
 */
public interface ContentService {
    //新增内容管理
    TaotaoResult insertContent(TbContent content);

    //内容管理分页查询
    EasyUIDataGridResult getContentList(Long categoryId, int page, int rows);

    //内容管理编辑
    TaotaoResult updateContent(TbContent content);

    //内容管理删除
    TaotaoResult deleteContent(Long id);

}
