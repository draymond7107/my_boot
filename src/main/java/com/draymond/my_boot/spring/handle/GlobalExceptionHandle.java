package com.draymond.my_boot.spring.handle;

import com.draymond.commons.exception.SysException;
import com.draymond.commons.exception.TokenException;
import com.draymond.commons.spring.JsonResult;
import com.draymond.commons.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 *
 * @Auther: ZhangSuchao
 * @Date: 2019/12/17 17:07
 */

/*
    @RestControllerAdvice是 @ControllerAdvice + @ResponseBody的注解。所以@RestControllerAdvice可以帮助我们通过一个横切点
        @ExceptionHandler来使用RestfulApi处理异常。

 */
@RestControllerAdvice
public class GlobalExceptionHandle {
    private Log logger = LogFactory.getLog(GlobalExceptionHandle.class);

    @ExceptionHandler(Exception.class)
    public JsonResult defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String url = request.getRequestURI() ;
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            url = request.getRequestURI() + "queryString==" + queryString;
        }
        JsonResult result = new JsonResult(false);
        result.setRet(500);
        result.setMsg("error");
        if (ex instanceof org.springframework.web.bind.MissingServletRequestParameterException) {
            result.setRet(400);
            result.setMsg("请输入参数：" + ex.getMessage());
        } else if (ex instanceof TokenException) {
            result.setMsg(ex.getMessage());
        } else if (ex instanceof SysException) {
            result.setRet(((SysException) ex).getErrorCode());
            result.setMsg(ex.getMessage());
        } else if (ex instanceof NoHandlerFoundException) {
            NoHandlerFoundException notFindEx = (NoHandlerFoundException) ex;
            result.setRet(404);
            result.setMsg("找不到该接口: ");
        } else if (ex instanceof org.springframework.web.HttpRequestMethodNotSupportedException) {
            result.setRet(403);
            result.setMsg("不支持该请求方式");
        } else {
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(ex.getMessage())) {
                result.setMsg(ex.getMessage());
            } else {
                result.setMsg("对不起！服务器趴窝了！" + ex.getClass().getName());
            }
        }
        logger.error("运行异常: " + "url=" + url + "    >>>> " + result.getMsg());
        if (logger.isDebugEnabled()) {  //日志为debug则打印
            ex.printStackTrace();
        }
        return result;
    }

}