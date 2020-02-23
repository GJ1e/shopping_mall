package com.taotao.portal.service;

import com.taotao.pojo.TbUserIntegral;

/**
 * @Author GJ1e
 * @Create 2020/2/23
 * @Time 13:43
 */
public interface UserIntegralService {
    //查询用户积分
    TbUserIntegral queryUserIntegral(Long userId);
}
