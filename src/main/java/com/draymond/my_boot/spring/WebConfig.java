package com.draymond.my_boot.spring;

import com.draymond.my_boot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
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
public class WebConfig implements WebMvcConfigurer {

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
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
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
        registry.addResourceHandler("hello.html").addResourceLocations("classpath:/resources");
        registry.addResourceHandler("web.html").addResourceLocations("classpath:/resources");
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
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
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
}