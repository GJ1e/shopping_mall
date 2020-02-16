package com.taotao.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author GJ1e
 * @Create 2020/2/7
 * @Time 16:10
 */
public class TestPageHelper {
    @Test
    public void testPageHelper(){
        //1.获得mapper的代理对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
        //2.设置分页
        PageHelper.startPage(1,30);
        //3.执行查询
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%手机%");
        List<TbItem> list = itemMapper.selectByExample(example);
        //4.取分页结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        System.out.println("total"+total);
        int pages = pageInfo.getPages();
        System.out.println("pages"+pages);
        int pageSize = pageInfo.getPageSize();
        System.out.println("pageSize"+pageSize);
    }
}
