package com.taotao.service;

import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/10
 * @Time 15:58
 * 内容分类管理
 */
public interface ContentCategoryService {
    //内容分类查询
    List<EasyUITreeNode> getContentCatList(Long parenId);

    //内容分类添加
    TaotaoResult insertCategory(Long parentId, String name);
}
