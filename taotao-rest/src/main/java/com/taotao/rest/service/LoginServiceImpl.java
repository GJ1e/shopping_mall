package com.taotao.rest.service;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.rest.component.JedisClient;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * @Author GJ1e
 * @Create 2020/2/14
 * @Time 21:35
 * 用户登录服务
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    TbUserMapper userMapper;
    @Autowired
    JedisClient jedisClient;

    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //校验用户名和密码
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        //取用户信息
        if (list==null || list.isEmpty()){
            return TaotaoResult.build(400,"用户名或密码错误");
        }
        TbUser user = list.get(0);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            return TaotaoResult.build(400,"用户名或密码错误");
        }
        //登陆成功，生成Token
        String token = UUID.randomUUID().toString();
        //key:REDIS_SESSION:{TOKEN}
        //value:user转换成json
        user.setPassword(null);
        //将用户信息写入到Redis
        jedisClient.set(REDIS_SESSION_KEY+":"+token, JsonUtils.objectToJson(user));
        //设置Session的过期时间
        jedisClient.expire(REDIS_SESSION_KEY+":"+token,SESSION_EXPIRE);
        //写cookie
        CookieUtils.setCookie(request,response,"TT_TOKEN",token);
        return TaotaoResult.ok(token);
    }

    /**
     * 从token中获取用户信息
     * @param token
     * @return
     */
    @Override
    public TaotaoResult getUserByToken(String token) {
        //根据token获取用户信息
        String json = jedisClient.get(REDIS_SESSION_KEY+":"+token);
        if (StringUtils.isBlank(json)){
            return TaotaoResult.build(500,"用户session已过期");
        }
        //把json转换成java对象
        TbUser user = JsonUtils.jsonToPojo(json,TbUser.class);
        //更新session的过期时间
        jedisClient.expire(REDIS_SESSION_KEY+":"+token,SESSION_EXPIRE);
        return TaotaoResult.ok(user);
    }

    /**
     * 安全退出，根据Token安全删除Redis中的用户信息
     * @param token
     * @return
     */
    @Override
    public TaotaoResult deleteUserByToken(String token) {
        jedisClient.del(REDIS_SESSION_KEY+":"+token);
        return TaotaoResult.ok();
    }
}
