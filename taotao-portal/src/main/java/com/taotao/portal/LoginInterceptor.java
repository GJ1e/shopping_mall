package com.taotao.portal;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author GJ1e
 * @Create 2020/2/15
 * @Time 20:48
 * 用户登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Value("${SSO_LOGIN_URL}")
    private String SSO_LOGIN_URL;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从token中取user
        TbUser user = userService.getUserByToken(request,response);
        if (user == null){
            response.sendRedirect(SSO_LOGIN_URL + "?redirectURL=" + request.getRequestURL());
            return false;
        }
        //把用户对象放入request中
        request.setAttribute("user",user);
        //如果没有过期，放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
