package com.taotao.service;

import com.taotao.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 17:41
 */
public interface ItemCatService {
    /**
     * 根据parentId来查询商品类别
     * 树形列表
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getItemCatList(long parentId);
}
