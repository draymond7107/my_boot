package com.draymond.my_boot.service;


import com.draymond.commons.utils.StringUtils;
import com.draymond.my_boot.dao.AdminMapper;
import com.draymond.my_boot.entity.Admin;
import com.draymond.my_boot.entity.AdminExample;
import com.draymond.my_boot.queryvo.BaseQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangSuchao
 * @create 2019/3/28
 * @since 1.0.0
 */
@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;


    public PageInfo<Admin> selectPageList(BaseQuery baseQueryVo) {
        PageHelper.startPage(baseQueryVo.getPageNo(), baseQueryVo.getPageSize());

        AdminExample adminExample = new AdminExample();
        adminExample.setOrderByClause("id asc");
        AdminExample.Criteria criteria = adminExample.createCriteria();
        String name = baseQueryVo.getName();
        if (StringUtils.isNotEmpty(name)) {
            //用户名
            criteria.andTrueNameEqualTo(name);
        }
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        PageInfo pageInfo = new PageInfo(adminList);
        return pageInfo;
    }
}
