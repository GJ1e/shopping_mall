package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author GJ1e
 * @Create 2020/2/15
 * @Time 20:28
 */
public interface UserService {
    TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
}
