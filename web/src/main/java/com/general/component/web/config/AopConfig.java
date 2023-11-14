package com.general.component.web.config;

import com.general.component.log.aspect.OpLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 注入日志切面
 * @author: liuyan
 * @create: 2022−10-23 9:28 PM
 */
@Configuration
public class AopConfig {

    @Bean
    public OpLogAspect opLogAspect() {
        return new OpLogAspect();
    }


}
