package com.taotao.portal.service;

import com.taotao.pojo.EasyUIResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.ADNode;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author GJ1e
 * @Create 2020/2/11
 * @Time 17:15
 * 内容管理Service
 */
@Service
public class ContentServiceImpl implements ContentService{
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;
    @Value("${REST_CONTENT_AD1_CID}")
    private String REST_CONTENT_AD1_CID;
    @Value("${REST_CONTENT_INDEXDATA}")
    private String REST_CONTENT_INDEXDATA;

    /**
     * 获得大广告位内容
     * @return
     */
    @Override
    public String getAD1List() {
        //调用服务获得数据
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD1_CID);
        //把json转换成java对象
        TaotaoResult result = TaotaoResult.formatToList(json, TbContent.class);
        //取data属性，内容列表
        List<TbContent> contentList = (List<TbContent>) result.getData();
        //把内容列表转换成ADNode列表
        List<ADNode> resultList = new ArrayList<>();
        for (TbContent tbContent : contentList) {
            ADNode node = new ADNode();
            node.setHeight(240);
            node.setWidth(670);
            node.setSrc(tbContent.getPic());

            node.setHeightB(240);
            node.setWidthB(670);
            node.setSrcB(tbContent.getPic2());

            node.setAlt(tbContent.getSubTitle());
            node.setHref(tbContent.getUrl());
            resultList.add(node);
        }
        //需要把resultList转换成json数据
        String resultJson = JsonUtils.objectToJson(resultList);
        return resultJson;
    }

    /**
     * 获取首页楼数据
     * @return
     */
    @Override
    public String getIndexDate() {
        JsonUtils result = JsonUtils.getInstance();

        //封装1楼数据
       creatIndexData(result,"101");
       return result.mapToJson();
    }

    private void creatIndexData(JsonUtils result, String dataId){
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_INDEXDATA + "?categoryId="+dataId+"&page=1&rows=10");
        //把json转换成java对象
        EasyUIResult easyUIResult = EasyUIResult.formatToList(json, TbContent.class);
        //取data属性，内容列表
        List<TbContent> contentList = (List<TbContent>) easyUIResult.getRows();
        if (contentList==null){
            return;
        }
        JsonUtils jsonUtils = JsonUtils.getInstance();
        for (int i = 0; i < contentList.size(); i++) {
            TbContent content = contentList.get(i);
            jsonUtils.put(String.valueOf(i+1),
                    JsonUtils.start("d",content.getPic()).put("e",content.getUrl())
                    .put("c",content.getSubTitle()).put("a",content.getTitleDesc())
                    .put("b",content.getTitle()).put("f",content.getContent()).get());
        }
        result.put(dataId,jsonUtils.get());
    }
}
