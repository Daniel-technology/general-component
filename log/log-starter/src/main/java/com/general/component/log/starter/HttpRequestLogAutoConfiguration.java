package com.general.component.log.starter;

import com.general.component.log.core.filter.HttpRequestLogFilter;
import com.general.component.log.core.prop.HttpRequestProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

/**
 * http自动注入
 *
 * @author littlesnail
 * @create 2023−04-18 4:10 PM
 * @since 0.6.0
 */
@Configuration
@ConditionalOnProperty(prefix = "logging.httprequest", name = "enable", havingValue = "true")
public class HttpRequestLogAutoConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "logging.httprequest")
    public HttpRequestProperties httpRequestProperties() {
        return new HttpRequestProperties();
    }

    /**
     * http日志记录
     */
    @Bean
    @ConditionalOnBean(HttpRequestProperties.class)
    @ConditionalOnMissingBean(HttpRequestLogFilter.class)
    public FilterRegistrationBean<HttpRequestLogFilter> logFilterRegistration(HttpRequestProperties httpRequestProperties) {
        FilterRegistrationBean<HttpRequestLogFilter> registration = new FilterRegistrationBean<>();
        registration.addUrlPatterns(httpRequestProperties.getUrlPatterns());
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new HttpRequestLogFilter(httpRequestProperties.isShowRegion()));
        registration.setName("httpRequestLogFilter");
        registration.setOrder(httpRequestProperties.getOrder() == null ?
                Integer.MIN_VALUE + 3 : httpRequestProperties.getOrder());
        return registration;
    }
}
