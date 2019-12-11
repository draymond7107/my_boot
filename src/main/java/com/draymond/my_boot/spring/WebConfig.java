package com.draymond.my_boot.spring;

import com.draymond.my_boot.cache.AdminCache;
import com.draymond.my_boot.cache.UserChche;
import com.draymond.my_boot.interceptor.LoginInterceptor;
import com.draymond.my_boot.session.UserSession;
import com.draymond.my_boot.spring.exception.MyExceptionResolver;
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
 * <p>
 * https://www.codercto.com/a/54069.html
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
     * 添加自定义方法参数处理器
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

    /**
     * 配置路由请求规则
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
    }

    /**
     * 内容协商配置
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

    /**
     * 注册自定义转化器
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
    }


    /**
     * 资源处理
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
     * 视图跳转控制器
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    }

    /**
     * 配置视图解析
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    }

    /**
     * 添加自定义返回结果处理器
     *
     * @param handlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
    }

    /**
     * 配置消息转换器。重载会覆盖默认注册的HttpMessageConverter
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    /**
     * 配置消息转换器 仅添加一个自定义的HttpMessageConverter.
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    /**
     * 配置异常转换器
     *
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyExceptionResolver());
    }


    /**
     * 添加异常转化器
     *
     * @param resolvers
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        // resolvers.add(new MyExceptionResolver());
        //   为什么使用这个不起作用
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

    /*
    使用@EnableWebMvc注解 等于 扩展了WebMvcConfigurationSupport，但是没有重写任何方法
    使用“extends WebMvcConfigurationSupport”方式（需要添加@EnableWebMvc），会屏蔽掉springBoot的@EnableAutoConfiguration中的设置
    使用“implement WebMvcConfigurer”可以配置自定义的配置，同时也使用了@EnableAutoConfiguration中的设置
    使用“implement WebMvcConfigurer + @EnableWebMvc”，会屏蔽掉springBoot的@EnableAutoConfiguration中的设置
    这里的“@EnableAutoConfiguration中的设置”是指，读取 application.properties 或 application.yml 文件中的配置。

    所以，如果需要使用springBoot的@EnableAutoConfiguration中的设置，那么就只需要“implement WebMvcConfigurer”即可。如果，需要自己扩展同时不使用@EnableAutoConfiguration中的设置，可以选择另外的方式。
     */
}