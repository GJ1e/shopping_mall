package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItemComment;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/21
 * @Time 22:27
 */
@Service
public class ItemCommentServiceImpl implements ItemCommentService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;

    @Value("${ITEM_COMMENT_URL}")
    private String ITEM_COMMENT_URL;

    @Value("${ITEM_QUERY_COMMENT_URL}")
    private String ITEM_QUERY_COMMENT_URL;

    @Override
    public TaotaoResult insertItemComment(TbItemComment itemComment) {
        String jsonData = JsonUtils.objectToJson(itemComment);
        String jsonResult = HttpClientUtil.doPostJson(REST_BASE_URL+ITEM_COMMENT_URL,jsonData);
        TaotaoResult result = TaotaoResult.formatToPojo(jsonResult,TaotaoResult.class);
        return TaotaoResult.ok(result);
    }

    @Override
    public List<TbItemComment> getItemCommentList(Long itemId) {
        String jsonData = HttpClientUtil.doGet(REST_BASE_URL+ITEM_QUERY_COMMENT_URL+itemId);
        List<TbItemComment> list = JsonUtils.jsonToList(jsonData,TbItemComment.class);
        return list;
    }
}
