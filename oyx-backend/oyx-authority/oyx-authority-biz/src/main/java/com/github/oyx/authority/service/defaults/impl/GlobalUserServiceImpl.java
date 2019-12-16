package com.github.oyx.authority.service.defaults.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.oyx.authority.dao.defaults.GlobalUserMapper;
import com.github.oyx.authority.enumeration.defaults.GlobalUser;
import com.github.oyx.authority.service.defaults.GlobalUserService;

/**
 * @author OYX
 * @date 2019-12-16 14:10
 */
@Service
public class GlobalUserServiceImpl extends ServiceImpl<GlobalUserMapper, GlobalUser> implements GlobalUserService {
    @Override
    public Boolean check(String tenantCode, String account) {
        return null;
    }
}
