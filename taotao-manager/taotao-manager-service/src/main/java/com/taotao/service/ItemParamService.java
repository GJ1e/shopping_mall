package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;

/**
 * @Author GJ1e
 * @Create 2020/2/8
 * @Time 21:19
 * 商品规格参数
 */
public interface ItemParamService {

    TaotaoResult getItemParamByCid(Long cid);

    TaotaoResult insertItemParam(Long cid, String paramData);

    //商品类目规格参数展示
    EasyUIDataGridResult getItemParamList(int page, int rows);

    //根据ID删除商品类目规格参数模板
    TaotaoResult deleteItemParam(Long id);
}
