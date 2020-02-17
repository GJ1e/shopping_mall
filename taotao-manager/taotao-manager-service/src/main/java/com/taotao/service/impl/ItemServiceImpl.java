package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 10:50
 *
 * 商品查询
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    TbItemMapper itemMapper;
    @Autowired
    TbItemDescMapper itemDescMapper;
    @Autowired
    TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        //根据主键查询
        //itemMapper.selectByPrimaryKey(itemId);

        //创建查询条件
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);

        List<TbItem> list = itemMapper.selectByExample(example);
        //判断List是否为空
        TbItem item = null;
        if (list != null && list.size()>0){
            item = list.get(0);
        }
        return item;
    }

    /**
     * 对商品进行分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page,rows);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        //返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    /**
     * 商品添加
     * @param item
     * @param desc
     * @param itemParam
     * @return
     */
    @Override
    public TaotaoResult createItem(TbItem item, String desc, String itemParam) {
        //生成商品ID
        long itemId = IDUtils.genItemId();
        //补全TbItem属性
        item.setId(itemId);
        //商品状态 1-正常  2-下架， 3-删除
        item.setStatus((byte) 1);
        //创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //插入商品表
        itemMapper.insert(item);
        //商品描述
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        //插入商品描述数据
        itemDescMapper.insert(itemDesc);

        //添加商品规格参数处理
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        //插入数据
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();
    }



    @Override
    public TaotaoResult getItemDescById(Long itemId) {
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);

        List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
        if (list!=null && list.size()>0){
            TbItemDesc itemDesc = list.get(0);
            return TaotaoResult.ok(itemDesc);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult getItemParamById(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);

        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list!=null && list.size()>0){
            TbItemParamItem itemParamItem = list.get(0);
            return TaotaoResult.ok(itemParamItem);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateItem(TbItem item, String desc, String itemParam) {
        //更新商品数据
        item.setStatus((byte)1);
        item.setUpdated(new Date());
        item.setCreated(new Date());
        itemMapper.updateByPrimaryKey(item);
        //更新商品描述
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.updateByPrimaryKeyWithBLOBs(itemDesc);

        //更新商品规格
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(item.getId());
        List<TbItemParamItem> itemParamList = itemParamItemMapper.selectByExampleWithBLOBs(example);
        for (TbItemParamItem tbItemParam: itemParamList) {
            tbItemParam.setParamData(itemParam);
            tbItemParam.setUpdated(new Date());
            tbItemParam.setCreated(new Date());
            itemParamItemMapper.updateByExampleWithBLOBs(tbItemParam,example);
        }
        return TaotaoResult.ok();
    }

    /**
     * 根据商品编号，删除商品
     * @param itemId
     * @return
     */
    @Override
    public TaotaoResult deleteItem(Long itemId) {
        //删除商品表
        itemMapper.deleteByPrimaryKey(itemId);
        //删除商品描述表
        itemDescMapper.deleteByPrimaryKey(itemId);
        //删除商品规格表
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        itemParamItemMapper.deleteByExample(example);

        return TaotaoResult.ok();
    }

    /**
     * 下架商品
     * @param itemId
     * @return
     */
    @Override
    public TaotaoResult updateItemStock(Long itemId) {
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //设置商品状态为下架
        item.setStatus((byte)2);
        //设置商品库存
        item.setNum(0);
        //更新商品数据
        itemMapper.updateByPrimaryKey(item);
        return TaotaoResult.ok();
    }


}
