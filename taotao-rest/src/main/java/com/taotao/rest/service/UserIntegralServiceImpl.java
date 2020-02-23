package com.taotao.rest.service;

import com.taotao.mapper.TbUserIntegralMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUserIntegral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author GJ1e
 * @Create 2020/2/23
 * @Time 11:13
 * 用户积分表
 */
@Service
public class UserIntegralServiceImpl implements UserIntegralService{
    @Autowired
    TbUserIntegralMapper userIntegralMapper;

    //插入用户积分
    @Override
    public TaotaoResult insertUserIntegral(TbUserIntegral userIntegral) {
        userIntegralMapper.insert(userIntegral);
        return TaotaoResult.ok();
    }

    @Override
    public TbUserIntegral queryUserIntegral(Long userId) {
        TbUserIntegral userIntegral = userIntegralMapper.queryUserIntegral(userId);
        return userIntegral;
    }
}
