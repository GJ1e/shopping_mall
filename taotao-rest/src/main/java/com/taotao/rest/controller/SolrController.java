package com.taotao.rest.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.rest.service.SolrService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 22:48
 * 导入商品数据到solr
 */
@Controller
public class SolrController {
    @Autowired
    private SolrService solrService;

    @RequestMapping("/importall")
    @ResponseBody
    public TaotaoResult importAll() {
        try {
            TaotaoResult result = solrService.importItems();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }


}
