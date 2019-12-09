package com.draymond.my_boot.session;

import com.draymond.commons.auth.AbsUserSession;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/8 16:10
 */
public class UserSession extends AbsUserSession {
    private String token;

    public UserSession() {
    }

    public void setToken(String token) {
        this.token = token;
    }

}