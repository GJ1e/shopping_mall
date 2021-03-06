package com.taotao.rest.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.*;
import com.taotao.rest.component.JedisClient;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/11
 * @Time 16:42
 * 内容查询服务
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    TbContentMapper contentMapper;
    @Autowired
    JedisClient jedisClient;

    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;

    @Override
    public List<TbContent> getContentList(Long cid) {
        //添加缓存
        //查数据库之前先查缓存，存在则直接返回
//        try {
//            //从Redis中取缓存数据
//            String json = jedisClient.hget(REDIS_CONTENT_KEY,cid+"");
//            if (!StringUtils.isBlank(json)){
//                //把json转换成list
//                List<TbContent> list = JsonUtils.jsonToList(json,TbContent.class);
//                return list;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        //缓存中没有在从数据库中查
        //根据cid查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);


        //返回结果之前，向缓存中添加数据
//        try {
//
//            jedisClient.hset(REDIS_CONTENT_KEY,cid+"", JsonUtils.objectToJson(list));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return list;
    }

    /**
     * 获取楼数据
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getIndexData(Long categoryId, int page, int rows) {
        //设置分页条件
        PageHelper.startPage(page, rows);
        //执行查询
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

        //取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        //返回处理结果
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    /**
     * 删除内容缓存
     * @param cid
     * @return
     */
    @Override
    public TaotaoResult syncContent(Long cid) {
        jedisClient.hdel(REDIS_CONTENT_KEY,cid+"");
        return TaotaoResult.ok();
    }
}
