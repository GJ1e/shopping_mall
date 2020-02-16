package com.taotao.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/9
 * @Time 17:48
 * 前端描述商品分类的pojo
 */
@Getter
@Setter
public class CatNode {
    @JsonProperty("u")
    private String url;

    @JsonProperty("n")
    private String name;

    @JsonProperty("i")
    private List items;
}
