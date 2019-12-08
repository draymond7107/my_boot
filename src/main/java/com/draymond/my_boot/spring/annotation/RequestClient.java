package com.draymond.my_boot.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 客户端常用request属性注解
 * 功能说明：<br>
 * 模块名称：<br>
 * 功能描述：<br>
 * 文件名称: <br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2016-8-29 下午10:01:01<br>
 * 系统版本：1.0.0<br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface RequestClient{
}
