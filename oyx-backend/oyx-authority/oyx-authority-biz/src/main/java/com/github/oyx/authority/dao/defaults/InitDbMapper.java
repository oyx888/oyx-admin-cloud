package com.github.oyx.authority.dao.defaults;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.annotation.SqlParser;

/**
 * 初始化数据库dao
 * @author OYX
 * @date 2019-12-22 15:55:20
 */
@Repository
@SqlParser(filter = true)
public interface InitDbMapper {
    /**
     * 创建数据库
     *
     * @param database
     * @return
     */
    int createDatabase(@Param("database") String database);
}
