package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * @Author GJ1e
 * @Create 2020/2/13
 * @Time 14:17
 */
public interface SearchService {
    SearchResult search(String keyword,int page,int rows);
}
