package com.taotao.rest.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 10:37
 */
@Getter
@Setter
public class SearchItem {
    private String id;
    private String title;
    private String sell_point;
    private Long price;
    private String image;
    private String category_name;
    private String item_desc;
}
