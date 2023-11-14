package com.general.component.web.config;

import com.general.component.log.resolver.WebContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置
 * @author: liuyan
 * @create: 2022−10-24 10:32 AM
 */
@Configuration
public class WebConfig {

    /**
     * 获取上下文
     *
     * @return
     */
    @Bean
    public WebContext webContext() {
        return new WebContext();
    }


}
