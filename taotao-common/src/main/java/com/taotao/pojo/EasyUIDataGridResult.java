package com.taotao.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 16:29
 * 后台分页接收数据的包装类
 */
@Getter
@Setter
public class EasyUIDataGridResult {

    private long total;
    private List<?> rows;
}
