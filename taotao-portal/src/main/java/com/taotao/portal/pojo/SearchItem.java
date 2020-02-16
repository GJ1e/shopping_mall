package com.taotao.portal.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author GJ1e
 * @Create 2020/2/12
 * @Time 10:37
 */

@Setter
public class SearchItem {
    private String id;
    private String title;
    private String sell_point;
    private Long price;
    private String image;
    private String category_name;
    private String item_desc;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public Long getPrice() {
        return price;
    }

    public String getImage() {
        if (image != null && !image.equals("")){
            String[] strings = image.split(",");
            return strings[0];
        }
        return image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getItem_desc() {
        return item_desc;
    }
}
