package com.github.oyx.database.mybatis.auth;

import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author OYX
 * @date 2019-12-13 14:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataScope extends HashMap {
    /**
     * 限制范围的字段名称 （除个人外）
     */
    private String scopeName = "org_id";
    /**
     * 限制范围为个人时的字段名称
     */
    private String selfScopeName = "create_user";
    /**
     * 当前用户ID
     */
    private Long userId;

    /**
     * 具体的数据范围
     */
    private List<Long> orgIds;
}
