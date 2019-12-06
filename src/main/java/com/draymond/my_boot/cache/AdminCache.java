package com.draymond.my_boot.cache;

import com.draymond.commons.utils.JsonUtils;
import com.draymond.commons.utils.StringUtils;
import com.draymond.my_boot.entity.Admin;
import com.draymond.my_boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/5 19:45
 */
@Component
public class AdminCache {

    private final static String ADMIN_ID_CACHE_KEY = "adminId:%s";
    private final static int ADMIN_ID_CACHE_TIME = 2 * 60 * 60;//缓存时间2小时

    private final static String ADMIN_TOKEN_CACHE_KEY = "adminToken:%s";
    private final static int ADMIN_TOKEN_CACHE_TIME = 2 * 60 * 60;//缓存时间2小时

    @Autowired
    private BaseCache baseCache;
    @Autowired
    private AdminService adminService;

    // ------------ adminId -------------
    private String getAdminIdCacheKey(int adminId) {
        return String.format(ADMIN_ID_CACHE_KEY, String.valueOf(adminId));
    }

    public void flushAdmin(Admin admin) {
        String adminCacheKey = getAdminIdCacheKey(admin.getId());
        baseCache.putString(adminCacheKey, admin, ADMIN_ID_CACHE_TIME);
    }

    public Admin getAdmin(int adminId) {
        String adminCacheKey = getAdminIdCacheKey(adminId);
        String string = baseCache.getString(adminCacheKey);

        if (StringUtils.isEmpty(string)) {
            return adminService.findById(adminId);
        }
        return JsonUtils.toJavaObject(string, Admin.class);
    }

    // ------------ token -------------
    private String getAdminTokenCacheKey(String token) {
        return String.format(ADMIN_TOKEN_CACHE_KEY, token);
    }


    public Admin getAdminSession(String token) {
        String key = getAdminTokenCacheKey(token);
        String string = baseCache.getString(key);
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        return JsonUtils.toJavaObject(string, Admin.class);
    }

    public void flushAdminSession(String token, Admin admin) {
        String key = getAdminTokenCacheKey(token);
        baseCache.putString(key, admin, ADMIN_TOKEN_CACHE_TIME);

        flushAdmin(admin);
    }

}