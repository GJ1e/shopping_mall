package com.taotao.rest.service;

import com.taotao.pojo.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 21:33
 * 用户登录
 */
public interface LoginService {
    TaotaoResult login(String userName, String passWord, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult getUserByToken(String token);
    TaotaoResult deleteUserByToken(String token);
}
