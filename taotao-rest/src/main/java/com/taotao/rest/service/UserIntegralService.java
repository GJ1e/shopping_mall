package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUserIntegral;

/**
 * @Author GJ1e
 * @Create 2020/2/23
 * @Time 11:10
 */
public interface UserIntegralService {
    //插入用户积分
    TaotaoResult insertUserIntegral(TbUserIntegral userIntegral);
    //查询用户积分
    TbUserIntegral queryUserIntegral(Long userId);
}
