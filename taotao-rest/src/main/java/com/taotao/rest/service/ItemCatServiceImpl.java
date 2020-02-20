package com.taotao.rest.service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/9
 * @Time 18:08
 * 商品分类列表查询
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    TbItemCatMapper itemCatMapper;

    @Override
    public ItemCatResult getItemCatList() {
        List catList = getItemCatList(0l);
        ItemCatResult result = new ItemCatResult();
        result.setData(catList);
        return result;
    }


    private List getItemCatList(Long parentId){
        //根据parentId查询列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List resultList = new ArrayList<>();
        int index = 0;
        for (TbItemCat tbItemCat : list) {
            if (index >=14){
                break;
            }
            //如果是父节点
            if (tbItemCat.getIsParent()) {
                CatNode node = new CatNode();
                node.setUrl("/search/"+tbItemCat.getId()+".html");
                //如果当前节点为第一级节点
                if (tbItemCat.getParentId() == 0) {
                    node.setName("<a href='/search/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                    //第一级节点不能超过14个元素，index为计数器
                    index++;
                } else {
                    node.setName(tbItemCat.getName());
                }
                node.setItems(getItemCatList(tbItemCat.getId()));
                //把node添加到列表
                resultList.add(node);
            } else {
                //如果是叶子节点
                String item = "/search/"+tbItemCat.getId()+".html|" + tbItemCat.getName();
                resultList.add(item);
            }
        }
        return resultList;


    }
}
