package com.draymond.my_boot.cache;

import com.draymond.my_boot.session.UserSession;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/9 17:27
 */
@Component
public class UserChche {

    public UserSession getUserSession(String token) {
        return new  UserSession();
    }
}