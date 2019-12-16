package com.github.oyx.authority.config.log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.github.oyx.authority.service.common.OptLogService;
import com.github.oyx.log.event.SysLogListener;

/**
 * 日志自动配置
 * @author OYX
 * @date 2019-12-16 14:50
 */
@EnableAsync
@Configuration
public class LogConfiguration {
    @Value("${oyx.mysql.biz-database:oyx_defaults}")
    private String database;

    @Bean
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener(database, (log) -> optLogService.save(log));
    }
}
