package com.taotao.mapper;

import com.taotao.pojo.TbUserIntegral;

/**
 * @Author GJ1e
 * @Create 2020/2/23
 * @Time 10:39
 */
public interface TbUserIntegralMapper {
    //查询用户积分
    TbUserIntegral queryUserIntegral (Long userId);
    //插入用户积分
    int insert(TbUserIntegral userIntegral);
    //更新用户积分
    int updateIntegral(TbUserIntegral userIntegral);
}
