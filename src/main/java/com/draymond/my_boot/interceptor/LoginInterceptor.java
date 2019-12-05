package com.draymond.my_boot.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/5 09:14
 */
@Configuration
public class LoginInterceptor implements HandlerInterceptor {

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
        // // TODO: 2019/12/5  从redis查询该token，等等

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
}