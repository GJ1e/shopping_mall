package com.taotao.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 21:19
 * 图片上传成功返回的JSON数据封装
 */
@Setter
@Getter
public class PictureResult {
    private int error;
    private String url;
    private String message;
}
