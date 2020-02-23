package com.taotao.pojo;

/**
 * @Author GJ1e
 * @Create 2020/2/23
 * @Time 10:35
 * 会员积分表
 */
public class TbUserIntegral {
    private Long userId;
    private String username;
    private String integral;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
