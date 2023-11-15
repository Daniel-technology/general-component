package com.general.component.log.starter;

import com.general.component.mvc.core.context.WebContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 上下文注入
 *
 * @author littlesnail
 * @create 2023−04-19 2:06 PM
 * @since 0.6.0
 */
@Configuration
@ConditionalOnMissingBean
public class WebContextAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WebContext.ContextHolder applicationContextHolder() {
        return new WebContext.ContextHolder();
    }
}
