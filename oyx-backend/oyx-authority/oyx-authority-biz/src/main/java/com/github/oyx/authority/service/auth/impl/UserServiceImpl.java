package com.github.oyx.authority.service.auth.impl;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.oyx.authority.dao.auth.UserMapper;
import com.github.oyx.authority.entity.auth.User;
import com.github.oyx.authority.service.auth.UserService;

/**
 * @author OYX
 * @date 2019-12-16 14:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public Map<String, Object> getDataScopeById(Long userId) {
        return null;
    }

    @Override
    public void updatePasswordErrorNumById(Long id) {
        baseMapper.incrPasswordErrorNumById(id);
    }
}
