package com.draymond.my_boot.controller;

import com.draymond.commons.abs.BaseController;
import com.draymond.commons.spring.JsonResult;
import com.draymond.my_boot.cache.AdminCache;
import com.draymond.my_boot.entity.Admin;
import com.draymond.my_boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/4 19:58
 */
@RestController
public class LoginController extends BaseController {


    @Autowired
    private AdminCache adminCache;
    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public JsonResult login(String account, String password) {

        Admin admin = adminService.getByAccount(account);
        if (admin == null || !password.equals(admin.getPassword())) {
            return sendError("账号或密码错误");
        }
        String token = UUID.randomUUID().toString();
        token=token.replace("-","");
        adminCache.flushAdminSession(token, admin);
        Map map=new HashMap() ;
        map.put("token",token);
        return sendSuccess(map);
    }
}