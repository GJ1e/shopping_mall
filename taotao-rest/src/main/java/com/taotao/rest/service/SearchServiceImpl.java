package com.taotao.rest.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.rest.dao.SearchDao;
import com.taotao.rest.pojo.SearchItem;
import com.taotao.rest.pojo.SearchResult;
import org.apache.ibatis.annotations.Param;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/13
 * @Time 11:40
 * Solr查询的Service
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    SearchDao searchDao;
    @Autowired
    TbItemMapper itemMapper;

    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%"+queryString+"%");
        List<TbItem> itemList = itemMapper.selectByExample(example);
        List<SearchItem> searchItemList = new ArrayList<>();
        for (TbItem tbItem:itemList) {
            SearchItem item = new SearchItem();
            item.setTitle(tbItem.getTitle());
            item.setSell_point(tbItem.getSellPoint());
            item.setPrice(tbItem.getPrice());
            item.setId(tbItem.getId().toString());
            item.setImage(tbItem.getImage());
            searchItemList.add(item);
        }
        SearchResult result = new SearchResult();
        result.setList(searchItemList);
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
        result.setRecordCount(pageInfo.getTotal());
        Long recordCount = result.getRecordCount();
        int pageCount = (int) (recordCount / rows);
        if (recordCount % rows > 0) {
            pageCount++;
        }
        result.setPageCount(pageCount);
        result.setCurPage(page);

        return result;
    }
//    @Override
//    public SearchResult search(String queryString, int page, int rows) throws Exception {
//        //创建查询条件
//        SolrQuery query = new SolrQuery();
//        //设置查询条件
//        query.setQuery(queryString);
//        //设置分页条件
//        query.setStart((page-1)*rows);
//        query.setRows(rows);
//        //设置默认搜索域
//        query.set("df", "item_title");
//        //设置高亮
//        query.setHighlight(true);
//        query.addHighlightField("item_title");
//        query.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
//        query.setHighlightSimplePost("</font>");
//        //执行查询
//        SearchResult searchResult = searchDao.search(query);
//        //计算总页数
//        Long recordCount = searchResult.getRecordCount();
//        int pageCount = (int) (recordCount / rows);
//        if (recordCount % rows > 0) {
//            pageCount++;
//        }
//        searchResult.setPageCount(pageCount);
//        searchResult.setCurPage(page);
//        return searchResult;
//    }
}
