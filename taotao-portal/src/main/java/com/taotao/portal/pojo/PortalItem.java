package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 12:30
 */
public class PortalItem extends TbItem {
    public String[] getImages(){
        String images = this.getImage();
        if (images!=null && !images.equals("")){
            String[] imgs = images.split(",");
            return imgs;
        }
        return null;
    }
}
