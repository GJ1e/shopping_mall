package com.taotao.portal.service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.PortalItem;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 12:02
 * 商品信息展示和管理Service
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_ITEM_BASE_URL}")
    private String REST_ITEM_BASE_URL;
    @Value("${REST_ITEM_DESC_URL}")
    private String REST_ITEM_DESC_URL;
    @Value("${REST_ITEM_PARAM_URL}")
    private String REST_ITEM_PARAM_URL;

    @Override
    public TbItem getItemById(Long itemId) {
        //根据商品ID查询商品基本信息
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_BASE_URL + itemId);
        //转换成Java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, PortalItem.class);
        TbItem item = (TbItem) taotaoResult.getData();
        return item;
    }

    @Override
    public String getItemDescById(Long itemId) {
        //根据商品ID查询商品描述
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_DESC_URL + itemId);
        //转换成Java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
        TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
        String desc = itemDesc.getItemDesc();
        return desc;
    }

    @Override
    public String getItemParamById(Long itemId) {
        //根据商品ID查询商品描述
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_PARAM_URL + itemId);
        //转换成Java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
        //取规格参数
        TbItemParamItem itemParamItem = (TbItemParamItem)taotaoResult.getData();
        String paramJson = itemParamItem.getParamData();
        //把规格参数的Json数据转换成java对象
        List<Map> paramList = JsonUtils.jsonToList(paramJson, Map.class);
        //遍历list生成html
        StringBuffer sb = new StringBuffer();

        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("	<tbody>\n");
        for (Map map : paramList) {
            sb.append("		<tr>\n");
            sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + map.get("group") + "</th>\n");
            sb.append("		</tr>\n");
            //取规格项
            List<Map> mapList2 = (List<Map>) map.get("params");
            for (Map map2:mapList2) {
                sb.append("		<tr>\n");
                sb.append("			<td class=\"tdTitle\">" + map2.get("k") + "</td>\n");
                sb.append("			<td>" + map2.get("v") + "</td>\n");
                sb.append("		</tr>\n");
            }
        }
        sb.append("	</tbody>\n");
        sb.append("</table>");

        return sb.toString();
    }
}
