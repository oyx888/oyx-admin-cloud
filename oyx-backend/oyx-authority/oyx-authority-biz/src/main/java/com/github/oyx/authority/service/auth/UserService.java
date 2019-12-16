package com.github.oyx.authority.service.auth;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.oyx.authority.entity.auth.User;

/**
 * @author OYX
 * @date 2019-12-16 14:20
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户id 查询数据范围
     *
     * @param userId 用户id
     * @return
     */
    Map<String, Object> getDataScopeById(Long userId);

    /**
     * 修改输错密码的次数
     *
     * @param id
     */
    void updatePasswordErrorNumById(Long id);
}
