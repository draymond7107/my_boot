package com.draymond.my_boot.spring;

import com.draymond.my_boot.cache.AdminCache;
import com.draymond.my_boot.cache.UserChche;
import com.draymond.my_boot.interceptor.LoginInterceptor;
import com.draymond.my_boot.session.UserSession;
import com.draymond.my_boot.spring.resolver.LoginArgumentResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * WebMvcConfigurer配置类其实是Spring内部的一种配置方式
 * 官方推荐直接实现WebMvcConfigurer或者直接继承WebMvcConfigurationSupport
 *
 * @Auther: ZhangSuchao
 * @Date: 2019/12/5 09:09
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig implements WebMvcConfigurer {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private AdminCache adminCache;
    @Autowired
    private UserChche userChche;

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/adm/**");

    }

    /**
     * 跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").maxAge(3600);
    }

    /**
     * 参数解析器
     *
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        logger.debug("addArgumentResolvers>>");
//        RequestArgumentResolver requestArgumentResolver = this.createRequestArgumentResolver();
//        //客户端拦截ClientInfo对象，提供自动绑定功能
//        if (requestArgumentResolver != null) {
//            logger.debug("添加请求参数拦截注解");
//            resolvers.add(requestArgumentResolver);
//        }
//        LoginArgumentResolver loginArgumentResolver = this.createLoginArgumentResolver();
//        //登录拦截系统
//        if (loginArgumentResolver != null) {
//            logger.debug("添加登录参数拦截注解");
//            resolvers.add(loginArgumentResolver);
//        }
//        logger.debug("添加注解自定义数量:" + resolvers.size());
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
    }

    /**
     * 配置内容裁决的一些参数
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    }

    /**
     * 默认静态资源处理器
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
    }


    /**
     * 静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
            addResoureHandler：指的是对外暴露的访问路径
            addResourceLocations：指的是内部文件放置的目录
         */
//        registry.addResourceHandler("hello.html").addResourceLocations("classpath:/resources");
//        registry.addResourceHandler("web.html").addResourceLocations("classpath:/resources");
    }


    /**
     * 页面跳转
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    /**
     * 视图解析器
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    }


    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    }

    /**
     * 信息转换器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    }

    /**
     * 注入获取登录解析
     *
     * @return LoginArgumentResolver
     */
    public LoginArgumentResolver createLoginArgumentResolver() {
        return new LoginArgumentResolver<UserSession>() {

            public UserSession getUserSession(String token) {
                return userChche.getUserSession(token);
            }

            public String authorizationToToken(String authorization) throws Exception {
                return authorization;
            }
        };
    }

}