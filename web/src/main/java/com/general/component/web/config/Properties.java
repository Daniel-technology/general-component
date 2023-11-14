package com.general.component.web.config;

import com.general.component.log.model.SensitiveRegex;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO
 * @author: liuyan
 * @create: 2023−02-07 11:44 AM
 */
@Configuration
public class Properties {

    @Bean
    @ConfigurationProperties(prefix = "logging.desensitize")
    public SensitiveRegex sensitiveRegex() {
        return new SensitiveRegex();
    }

}
