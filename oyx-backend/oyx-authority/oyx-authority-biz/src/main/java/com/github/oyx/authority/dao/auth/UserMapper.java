package com.github.oyx.authority.dao.auth;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.oyx.authority.entity.auth.User;

/**
 * @author OYX
 * @date 2019-12-16 14:29
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 递增 密码错误次数
     *
     * @param id
     * @return
     */
    int incrPasswordErrorNumById(@Param("id") Long id);
}
