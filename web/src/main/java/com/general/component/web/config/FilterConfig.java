package com.general.component.web.config;

import com.general.component.log.filter.HttpRequestLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * @description: 日志拦截器
 * @author: liuyan
 * @create: 2022−10-21 5:52 PM
 */
@Configuration
public class FilterConfig {

    /**
     * 日志记录
     */
    @Bean
    public FilterRegistrationBean<HttpRequestLogFilter> logFilterRegistration() {
        FilterRegistrationBean<HttpRequestLogFilter> registration = new FilterRegistrationBean<>();
        registration.addUrlPatterns("/*");
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new HttpRequestLogFilter(true));
        registration.setName("httpRequestLogFilter");
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }
}
