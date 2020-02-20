package com.taotao.rest.service;

import com.taotao.rest.pojo.SearchResult;


/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 11:21
 */

public interface SearchService {
    SearchResult search(String queryString, int page, int rows) throws Exception;
    SearchResult searchItemByCid(Long itemCid, int page, int rows) throws Exception;


//    public List<TbItem> getTitleLike(String title){
//        TbItemExample example = new TbItemExample();
//        TbItemExample.Criteria criteria = example.createCriteria();
//        criteria.andTitleLike("%"+title+"%");
//        PageHelper
//        List<TbItem> list = itemMapper.selectByExample(example);
//       return list;
//    }
}
