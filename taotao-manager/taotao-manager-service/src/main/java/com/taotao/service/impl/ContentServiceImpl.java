package com.taotao.service.impl;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author GJ1e
 * @Create 2020/2/10
 * @Time 17:58
 * 内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    TbContentMapper contentMapper;

    /**
     * 添加内容
     *
     * @param content
     * @return
     */
    @Override
    public TaotaoResult insertContent(TbContent content) {
        //补全content中的数据
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入数据
        contentMapper.insert(content);
        return TaotaoResult.ok();
    }
}
