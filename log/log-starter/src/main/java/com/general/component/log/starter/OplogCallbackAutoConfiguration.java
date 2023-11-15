package com.general.component.log.starter;

import com.general.component.log.core.aspect.OpLogAspect;
import com.general.component.log.core.prop.OplogCallbackProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 操作日志
 *
 * @author littlesnail
 * @create 2023−04-18 4:11 PM
 * @since 0.6.0
 */
@Configuration
@ConditionalOnProperty(prefix = "logging.oplogcallback", name = "enable", havingValue = "true")
public class OplogCallbackAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "logging.oplogcallback")
    public OplogCallbackProperties oplogCallbackProperties() {
        return new OplogCallbackProperties();
    }

    @Bean
    @ConditionalOnBean(OplogCallbackProperties.class)
    @ConditionalOnMissingBean(OpLogAspect.class)
    public OpLogAspect opLogAspect() {
        return new OpLogAspect();
    }
}
