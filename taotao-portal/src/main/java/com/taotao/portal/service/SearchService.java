package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * @Author GJ1e
 * @Create 2020/2/13
 * @Time 14:17
 */
public interface SearchService {
    //主页关键字模糊搜索
    SearchResult search(String keyword,int page,int rows);
    //主页商品分类Id搜索
    SearchResult searchItemByCid(Long itemCid,int page,int rows);

}
