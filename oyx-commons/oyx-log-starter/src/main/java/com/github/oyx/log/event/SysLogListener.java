package com.github.oyx.log.event;

import java.util.function.Consumer;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import com.github.oyx.context.BaseContextHandler;
import com.github.oyx.log.entity.OptLogDTO;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步监听日志事件
 * @author OYX
 * @date 2019-12-15 15:49
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    private String database;
    private Consumer<OptLogDTO> consumer;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        OptLogDTO sysLog = (OptLogDTO) event.getSource();
        if (sysLog == null || StrUtil.isEmpty(sysLog.getTenantCode())) {
            log.warn("租户编码不存在，忽略操作日志=={}", sysLog.getRequestUri());
            return;
        }
        BaseContextHandler.setDatabase(database);
        BaseContextHandler.setTenant(sysLog.getTenantCode());

        consumer.accept(sysLog);
    }
}
