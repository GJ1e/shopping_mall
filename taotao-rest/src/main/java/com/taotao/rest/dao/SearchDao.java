package com.taotao.rest.dao;

import com.taotao.rest.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @Author GJ1e
 * @Create 2020/2/13
 * @Time 11:02
 * 操作Solr的Dao
 */
public interface SearchDao {
    SearchResult search(SolrQuery query) throws Exception;
}
