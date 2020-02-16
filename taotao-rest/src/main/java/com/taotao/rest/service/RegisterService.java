package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 18:33
 * 用户注册数据校验
 */
public interface RegisterService {
    //用户数据校验
    TaotaoResult checkData(String param,int type);
    //用户注册
    TaotaoResult register(TbUser user);
}
