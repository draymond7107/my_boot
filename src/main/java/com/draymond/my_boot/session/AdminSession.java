package com.draymond.my_boot.session;

import com.draymond.commons.auth.AbsAdminSession;
import com.draymond.commons.auth.AbsUserSession;
import com.draymond.my_boot.entity.Admin;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/8 16:09
 */
public class AdminSession extends AbsAdminSession {
    private String token;

    public AdminSession() {
    }

    public AdminSession(String token) {
        this.token = token;
    }

    public AdminSession(String token, Admin admin) {
        this.setToken(token);
    }

    public void setToken(String token) {
        this.token = token;
    }
}