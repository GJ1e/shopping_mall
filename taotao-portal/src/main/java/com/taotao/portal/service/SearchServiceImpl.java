package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author GJ1e
 * @Create 2020/2/13
 * @Time 14:19
 * 搜索服务实现
 */
@Service
public class SearchServiceImpl implements SearchService{

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Value("${SEARCH_CID_URL}")
    private String SEARCH_CID_URL;

    @Override
    public SearchResult search(String keyword, int page, int rows) {
        //调用服务查询商品列表
        Map<String,String> map = new HashMap<>();
        map.put("keyword",keyword);
        map.put("page",page+"");
        map.put("rows",rows+"");
        //调用服务
        String json = HttpClientUtil.doGet(SEARCH_BASE_URL,map);
        //转换成Java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
        //取返回的结果
        SearchResult searchResult = (SearchResult) taotaoResult.getData();

        return searchResult;
    }
    @Override
    public SearchResult searchItemByCid(Long ItemCid, int page, int rows) {
        //调用服务查询商品列表
        Map<String,String> map = new HashMap<>();
        map.put("page",page+"");
        map.put("rows",rows+"");
        //调用服务
        String json = HttpClientUtil.doGet(SEARCH_CID_URL+ItemCid+"",map);
        //转换成Java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
        //取返回的结果
        SearchResult searchResult = (SearchResult) taotaoResult.getData();

        return searchResult;
    }
}
