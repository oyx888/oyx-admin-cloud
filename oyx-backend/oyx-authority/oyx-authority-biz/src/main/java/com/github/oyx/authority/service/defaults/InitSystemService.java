package com.github.oyx.authority.service.defaults;

/**
 * 初始化系统
 * @author OYX
 * @date
 */
public interface InitSystemService {
    /**
     * 初始化系统
     * @param tenant
     */
    void init(String tenant);

    /**
     * 初始化数据库
     * @param tenant
     */
    void initDatabases(String tenant);

    /**
     * 初始化表
     * @param tenant
     */
    void initTables(String tenant);

    /**
     * 初始化数据
     * @param tenant
     */
    void initData(String tenant);
}
