package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.rest.mapper.ItemMapper;
import com.taotao.rest.pojo.SearchItem;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 22:43
 * solr导入数据库
 */
@Service
public class SolrServiceImpl implements SolrService {
    @Autowired
    SolrServer solrServer;
    @Autowired
    ItemMapper itemMapper;

    /**
     * 将数据库信息导入到solr索引列表
     * @return
     */
    @Override
    public TaotaoResult importItems() throws Exception{
        //查询数据库获得商品列表
        List<SearchItem> itemList = itemMapper.getItemList();
        //遍历列表
        for (SearchItem item : itemList) {
            //创建文档对象
            SolrInputDocument document = new SolrInputDocument();
            //添加域
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSell_point());
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategory_name());
            document.addField("item_desc", item.getItem_desc());
            //写入索引库
            solrServer.add(document);
        }
        //提交
        solrServer.commit();
        return TaotaoResult.ok();
    }
}
