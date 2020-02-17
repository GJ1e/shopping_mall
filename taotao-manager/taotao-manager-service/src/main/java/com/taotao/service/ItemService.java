package com.taotao.service;

import com.taotao.pojo.*;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 10:46
 */

public interface ItemService {
    //根据商品ID查询商品
    TbItem getItemById(Long itemId);

    //根据传入的数据进行商品分页查询
    EasyUIDataGridResult getItemList(int page, int rows);

    //后台添加商品
    TaotaoResult createItem(TbItem item, String desc, String itemParam);

    //根据商品ID查询商品描述
    TaotaoResult getItemDescById(Long itemId);

    //根据商品ID查询商品规格
    TaotaoResult getItemParamById(Long itemId);
    //根据商品ID更新商品
    TaotaoResult updateItem(TbItem item,String desc, String itemParam);
    //根据商品ID删除商品
    TaotaoResult deleteItem(Long itemId);
    //根据商品ID下架商品
    TaotaoResult updateItemStock(Long itemId);


}
