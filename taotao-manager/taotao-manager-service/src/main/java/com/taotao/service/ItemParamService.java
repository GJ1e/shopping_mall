package com.taotao.service;

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
}
