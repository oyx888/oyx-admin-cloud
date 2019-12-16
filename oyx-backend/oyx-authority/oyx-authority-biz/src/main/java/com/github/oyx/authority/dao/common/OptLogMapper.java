package com.github.oyx.authority.dao.common;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.oyx.authority.entity.common.OptLog;

/**
 * 系统日志
 * @author OYX
 */
@Repository
public interface OptLogMapper extends BaseMapper<OptLog> {
}
