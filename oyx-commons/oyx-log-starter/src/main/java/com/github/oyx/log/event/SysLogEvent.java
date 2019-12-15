package com.github.oyx.log.event;

import org.springframework.context.ApplicationEvent;

import com.github.oyx.log.entity.OptLogDTO;

/**
 * @author OYX
 * @date 2019-12-15 15:37
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(OptLogDTO source) {
        super(source);
    }
}
