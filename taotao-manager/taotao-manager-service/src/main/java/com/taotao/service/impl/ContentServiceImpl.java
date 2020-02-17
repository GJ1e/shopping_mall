package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 内容分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
        //设置分页条件
        PageHelper.startPage(page, rows);
        //执行查询
        TbContentExample example = new TbContentExample();
        List<TbContent> list = new ArrayList<>();
        if (categoryId != 0){
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(categoryId);
            list = contentMapper.selectByExampleWithBLOBs(example);
        }else{

            list = contentMapper.selectByExampleWithBLOBs(example);
        }
        //取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        //返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    /**
     * 内容管理更新
     * @param content
     * @return
     */
    @Override
    public TaotaoResult updateContent(TbContent content) {
        content.setUpdated(new Date());
        contentMapper.updateByPrimaryKey(content);
        return TaotaoResult.ok();
    }

    /**
     * 内容管理删除
     * @param id
     * @return
     */
    @Override
    public TaotaoResult deleteContent(Long id) {
        contentMapper.deleteByPrimaryKey(id);
        return TaotaoResult.ok();
    }
}
