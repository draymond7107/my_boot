package com.draymond.my_boot.controller;


import com.draymond.commons.abs.BaseController;
import com.draymond.commons.spring.JsonResult;
import com.draymond.my_boot.entity.Admin;
import com.draymond.my_boot.queryvo.BaseQuery;
import com.draymond.my_boot.service.AdminService;
import com.draymond.my_boot.session.AdminSession;
import com.draymond.my_boot.spring.annotation.LoginedAuth;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员管理
 *
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    protected Log logger = LogFactory.getLog(getClass());

    @Autowired
    private AdminService adminService;

    @RequestMapping("/selectAdminPageList")
    public JsonResult selectAdminPageList(@LoginedAuth AdminSession session, BaseQuery baseQueryVo) {

        PageInfo<Admin> pageInfo = adminService.selectPageList(baseQueryVo);
        int i = 5 / 0;
        return sendSuccess(pageInfo);
    }
}
