package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 22:20
 * 获取配置文件中图片的上传路径
 */
@Service
public class PropertieService {
    @Value("${REPOSITORY_PATH}")
    public String REPOSITORY_PATH;

    @Value("${IMAGE_BASE_URL}")
    public String IMAGE_BASE_URL;

//    @Value("${TAOTAO_WEB_URL}")
//    public String TAOTAO_WEB_URL;
}
