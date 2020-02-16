package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author GJ1e
 * @Create 2020/2/15
 * @Time 20:30
 * 拦截器，用户登录拦截。
 */
@Service
public class UserServiceImpl implements UserService{
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${SSO_USER_TOKEN_SERVICE}")
    private String SSO_USER_TOKEN_SERVICE;

    @Override
    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) {
        try{
            //从cookie中取token
            String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
            //判断token是否有值
            if (StringUtils.isBlank(token)){
                return null;
            }
            //调用服务查询用户信息
            String json = HttpClientUtil.doGet(REST_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
            //把json转换成Java对象
            TaotaoResult result = TaotaoResult.format(json);
            if (result.getStatus() != 200){
                return null;
            }
            //取用户对象
            result = TaotaoResult.formatToPojo(json,TbUser.class);
            TbUser user = (TbUser)result.getData();
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
