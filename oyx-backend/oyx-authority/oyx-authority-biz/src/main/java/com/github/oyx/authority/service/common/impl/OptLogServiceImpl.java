package com.github.oyx.authority.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.oyx.authority.dao.common.OptLogMapper;
import com.github.oyx.authority.entity.common.OptLog;
import com.github.oyx.authority.service.common.OptLogService;
import com.github.oyx.dozer.DozerUtils;
import com.github.oyx.log.entity.OptLogDTO;

/**
 * @author OYX
 * @date 2019-12-16 14:55
 */
@Service
public class OptLogServiceImpl extends ServiceImpl<OptLogMapper, OptLog> implements OptLogService {
    @Autowired
    DozerUtils dozer;

    @Override
    public boolean save(OptLogDTO entity) {
        return super.save(dozer.map(entity, OptLog.class));
    }
}
