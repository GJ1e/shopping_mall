package com.taotao.rest.dao;

import com.taotao.rest.pojo.SearchItem;
import com.taotao.rest.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author GJ1e
 * @Create 2020/2/13
 * @Time 11:03
 * solr查询索引域
 */
@Repository
public class SearchDaoImpl implements SearchDao{
    @Autowired
    SolrServer solrServer;
    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        QueryResponse response = solrServer.query(query);
        //去结果列表
        SolrDocumentList solrDocumentList = response.getResults();
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            item.setId((String) solrDocument.get("id"));
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            if (list != null && list.size() > 0) {
                //取高亮后的结果
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            item.setTitle(itemTitle);

            //添加到列表
            itemList.add(item);
        }
        SearchResult result = new SearchResult();
        result.setList(itemList);
        //取查询结果总数量
        result.setRecordCount(solrDocumentList.getNumFound());
        return result;
    }
}
