package com.taotao.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 17:38
 * 商品类别
 * 树形列表的包装类
 */
@Getter
@Setter
public class EasyUITreeNode {
    private long id;
    private  String text;
    private  String state;

}
