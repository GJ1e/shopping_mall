package com.taotao.rest.service;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 11:06
 * 商品管理Service
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    TbItemMapper itemMapper;
    @Autowired
    TbItemDescMapper itemDescMapper;
    @Autowired
    TbItemParamItemMapper itemParamItemMapper;

    /**
     * //根据商品Id查询商品信息，到前端展示
     * @param ItemId
     * @return
     */
    @Override
    public TbItem getItemById(Long ItemId) {
        TbItem item = itemMapper.selectByPrimaryKey(ItemId);
        return item;
    }

    /**
     * //根据商品Id查询商品描述，到前端展示
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        return itemDesc;
    }

    /**
     * //根据商品Id查询商品规格参数，到前端展示
     * @param itemId
     * @return
     */
    @Override
    public TbItemParamItem getItemParamById(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        //取规格参数
        if (list!=null && list.size()>0){
            TbItemParamItem itemParamItem = list.get(0);
            return itemParamItem;
        }
        return null;
    }
}
