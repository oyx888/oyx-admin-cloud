package com.github.oyx.authority.dao.defaults;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.oyx.authority.enumeration.defaults.GlobalUser;

/**
 * Mapper 接口
 * 全局账号
 * @author OYX
 */
@Repository
@SqlParser(filter = true)
public interface GlobalUserMapper extends BaseMapper<GlobalUser> {
}
