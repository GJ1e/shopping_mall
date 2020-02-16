package com.taotao.rest.mapper;

import com.taotao.rest.pojo.SearchItem;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 22:28
 * solr导入数据
 */
public interface ItemMapper {
    List<SearchItem> getItemList();
}
