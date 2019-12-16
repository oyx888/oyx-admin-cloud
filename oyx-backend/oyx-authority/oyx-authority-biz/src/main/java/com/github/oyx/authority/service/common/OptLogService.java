package com.github.oyx.authority.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.oyx.authority.entity.common.OptLog;
import com.github.oyx.log.entity.OptLogDTO;

public interface OptLogService extends IService<OptLog> {

    /**
     * 保存日志
     *
     * @param entity
     * @return
     */
    boolean save(OptLogDTO entity);
}
