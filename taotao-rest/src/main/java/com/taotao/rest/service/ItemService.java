package com.taotao.rest.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 11:05
 */
public interface ItemService {
    //根据商品Id查询商品信息，到前端展示
    TbItem getItemById(Long itemId);
    //根据商品Id查询商品描述，到前端展示
    TbItemDesc getItemDescById(Long itemId);
    //根据商品Id查询商品规格参数，到前端展示
    TbItemParamItem getItemParamById(Long itemId);

}
