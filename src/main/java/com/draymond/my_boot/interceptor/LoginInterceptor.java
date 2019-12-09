package com.draymond.my_boot.interceptor;

import com.draymond.commons.constants.Constants;
import com.draymond.commons.spring.SpringContext;
import com.draymond.commons.utils.StringUtils;
import com.draymond.my_boot.cache.AdminCache;
import com.draymond.my_boot.session.AdminSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/5 09:14
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private AdminCache adminCache;

    /**
     * 访问接口之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        String url = new String(requestURL);    //方法返回客户端发出请求时的完整URL
        String token = request.getHeader("adminToken");
        if (StringUtils.isEmpty(token)) {
            sendJsonError(request, response, 40001, "权限错误[获取不到授权信息]");
            return false;
        }
        if (adminCache == null) {
            adminCache = SpringContext.getBean("adminCache", AdminCache.class);
        }
        AdminSession adminSession = adminCache.getAdminSession(token);
        if (adminSession == null) {
            sendJsonError(request, response, 40002, "权限错误(登录超时)");
            return false;
        }
        request.setAttribute(Constants.ADMIN_SESSION_KEY,adminSession);
        return true;
    }

    /**
     * Controller之后调用，视图渲染之前，如果控制器Controller出现了异常，则不会执行此方法
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    /**
     * 不管有没有异常，这个afterCompletion都会被调用，用于资源清理
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }


    protected void sendJsonError(HttpServletRequest request, HttpServletResponse response, int ret, String msg) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Accept,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Vary", "Origin,Accept-Encoding");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("expires", "0");
        response.setContentType("text/json; charset=UTF-8");
        response.getWriter().write("{\"msg\":\"" + msg + "\",\"ret\":" + ret + "}");
    }
}