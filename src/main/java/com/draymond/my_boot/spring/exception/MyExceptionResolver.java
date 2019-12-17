package com.draymond.my_boot.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/11 18:43
 */
//@Component
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        // 异常处理，例如将异常信息存储到数据库
        //  exceptionLogDao.save(ex);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("userName", "zsc");
        modelAndView.addObject("sex", "M");
        modelAndView.addObject("email", "draymond7107@sina.com");
        modelAndView.setStatus(HttpStatus.NOT_IMPLEMENTED);

        modelAndView.setViewName("index.html");

        if (ex instanceof ErrorException) {
            //--------
        }
        // 视图显示专门的错误页
        return modelAndView;
    }
}