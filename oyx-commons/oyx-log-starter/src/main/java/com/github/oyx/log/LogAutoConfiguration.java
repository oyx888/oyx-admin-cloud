package com.github.oyx.log;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.github.oyx.log.aspect.SysLogAspect;

import lombok.AllArgsConstructor;

/**
 * 日志自动配置
 * @author OYX
 * @date 2019-12-15 14:48
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
@ConditionalOnProperty(name = "oyx.log.enabled", havingValue = "true", matchIfMissing = true)
public class LogAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public SysLogAspect sysLogAspect() {
        return new SysLogAspect();
    }
}
