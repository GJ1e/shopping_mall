package com.taotao.portal.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 10:43
 */
@Getter
@Setter
public class SearchResult {
    private List<SearchItem> list;
    private Long recordCount;
    private int pageCount;
    private int curPage;
}
