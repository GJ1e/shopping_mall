package com.taotao.rest.controller;

import com.taotao.pojo.EasyUIResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUserIntegral;
import com.taotao.rest.service.UserIntegralService;
import com.taotao.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author GJ1e
 * @Create 2020/2/23
 * @Time 11:28
 * 用户积分服务Controller
 */
@Controller
public class UserIntegralController {
    @Autowired
    UserIntegralService userIntegralService;

    @RequestMapping("/user/queryintegral/{userId}")
    @ResponseBody
    public TaotaoResult queryUserIntegral(@PathVariable("userId") Long userId){
        try{
            TbUserIntegral userIntegral = userIntegralService.queryUserIntegral(userId);
            return TaotaoResult.ok(userIntegral);
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping(value = "/user/insertintegral",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertUserIntegral(TbUserIntegral userIntegral){
        System.out.println(123);
         TaotaoResult result = userIntegralService.insertUserIntegral(userIntegral);
        return result;
    }


}
