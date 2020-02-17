package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/17
 * @Time 20:51
 * 后台用户服务
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    TbUserMapper userMapper;

    /**
     * 后台会员列表分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getUserList(int page, int rows) {
        PageHelper.startPage(page,rows);
        TbUserExample example = new TbUserExample();
        List<TbUser> list = userMapper.selectByExample(example);
        PageInfo<TbUser> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }
}
