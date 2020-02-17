package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;

/**
 * @Author GJ1e
 * @Create 2020/2/17
 * @Time 20:52
 */
public interface UserService {
    //后台分页查询用户
    EasyUIDataGridResult getUserList(int page, int rows);
}
