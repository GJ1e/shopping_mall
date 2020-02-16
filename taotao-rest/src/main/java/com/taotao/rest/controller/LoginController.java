package com.taotao.rest.controller;

import com.taotao.pojo.TaotaoResult;
import com.taotao.rest.service.LoginService;
import com.taotao.utils.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 22:07
 * 用户登录接口
 */
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult login(String username, String password,
                              HttpServletRequest request, HttpServletResponse response){
        try {
            TaotaoResult result = loginService.login(username,password,request,response);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback){
        try{
            TaotaoResult result = loginService.getUserByToken(token);
            //支持jsonp调用
            if (StringUtils.isNotBlank(callback)){
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/user/logout/{token}")
    @ResponseBody
    public Object deleteUserByToken(@PathVariable String token,String callback){
        try{
            TaotaoResult result = loginService.deleteUserByToken(token);
            if (StringUtils.isNotBlank(callback)){
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,"用户token不存在");
        }
    }
}
