package com.taotao.service.impl;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.EasyUITreeNode;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/10
 * @Time 16:00
 * 内容分类管理Service
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    TbContentCategoryMapper contentCategoryMapper;

    /**
     * 内容分类查询
     * @param parenId
     * @return
     */
    @Override
    public List<EasyUITreeNode> getContentCatList(Long parenId) {
        //根据ParentID查询子节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parenId);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        //将list转换成EasyUITreeNode
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {

            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            //添加到列表
            resultList.add(node);
        }

        return resultList;
    }

    /**
     * 内容分类添加
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public TaotaoResult insertCategory(Long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setParentId(parentId);
        //1-正常 2-删除
        contentCategory.setStatus(1);
        contentCategory.setIsParent(false);
        //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //插入数据
        contentCategoryMapper.insert(contentCategory);
        //取返回主键
        Long id = contentCategory.getId();
        //判断父节点的isParent属性
        //查询父节点
        TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentNode.getIsParent()){
            parentNode.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentNode);
        }
        //返回主键
        return TaotaoResult.ok(id);
    }
}
