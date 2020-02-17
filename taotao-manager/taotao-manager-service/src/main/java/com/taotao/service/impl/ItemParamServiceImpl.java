package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/8
 * @Time 21:19
 * 商品规格参数模板管理Service
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    TbItemParamMapper itemParamMapper;

    /**
     * 根据cid查询这个类的商品规格参数是否已被添加过
     * @param cid
     * @return
     */
    @Override
    public TaotaoResult getItemParamByCid(Long cid){
        //根据Cid查询规格参数模板
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        //执行查询
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if (list!=null && list.size()>0){
            TbItemParam itemParam = list.get(0);
            return TaotaoResult.ok(itemParam);
        }
        return TaotaoResult.ok();
    }

    /**
     * 添加商品类别规格参数到数据库
     * @param cid
     * @param paramData
     * @return
     */
    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData) {
        //创建一个pojo
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入记录
        itemParamMapper.insert(itemParam);

        return TaotaoResult.ok();
    }

    /**
     * 商品规格参数分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {
        //设置分页条件
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);


        //取分页信息
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        //返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TaotaoResult deleteItemParam(Long id) {
        itemParamMapper.deleteByPrimaryKey(id);
        return TaotaoResult.ok();
    }


}
