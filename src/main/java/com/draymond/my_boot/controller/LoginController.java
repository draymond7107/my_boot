package com.draymond.my_boot.controller;

import com.draymond.commons.abs.BaseController;
import com.draymond.commons.spring.JsonResult;
import com.draymond.my_boot.entity.Admin;
import com.draymond.my_boot.queryvo.BaseQuery;
import com.draymond.my_boot.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/4 19:58
 */
@RestController
public class LoginController extends BaseController {


    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public JsonResult login(String account, String password) {

        Admin admin = adminService.getByAccount(account);
        if (admin == null || !password.equals(admin.getPassword())) {
            return sendError("账号或密码错误");
        }
        return sendSuccess();
    }
}