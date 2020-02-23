package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUserIntegral;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author GJ1e
 * @Create 2020/2/23
 * @Time 13:45
 * 查询用户积分Service
 */
@Service
public class UserIntegralServiceImpl implements UserIntegralService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${QUERY_USER_INTEGRAL_URL}")
    private String QUERY_USER_INTEGRAL_URL;

    /**
     * 查询用户积分
     * @param userId
     * @return
     */
    @Override
    public TbUserIntegral queryUserIntegral(Long userId){
        try{
            String jsonData = HttpClientUtil.doGet(REST_BASE_URL+QUERY_USER_INTEGRAL_URL+userId);
            TaotaoResult result = TaotaoResult.format(jsonData);
            if (result.getStatus()!=200){
                return null;
            }
            result = TaotaoResult.formatToPojo(jsonData,TbUserIntegral.class);
            TbUserIntegral userIntegral = (TbUserIntegral) result.getData();
            return userIntegral;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
