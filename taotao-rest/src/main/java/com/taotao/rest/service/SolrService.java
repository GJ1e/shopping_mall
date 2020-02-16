package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;

/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 22:41
 *
 */
public interface SolrService {

    //solr导入商品信息
    TaotaoResult importItems() throws Exception;



}
