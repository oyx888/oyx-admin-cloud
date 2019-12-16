package com.github.oyx.authority.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.oyx.authority.service.auth.UserService;
import com.github.oyx.database.datasource.BaseDbConfiguration;
import com.github.oyx.database.mybatis.auth.DataScopeInterceptor;
import com.github.oyx.utils.SpringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author OYX
 * @date 2019-12-16 15:00
 */
@Configuration
@Slf4j
@MapperScan(
        basePackages = {
                "com.github.oyx.authority.dao",
        },
        annotationClass = Repository.class,
        sqlSessionFactoryRef = "authoritySqlSessionFactory")
@EnableConfigurationProperties(MybatisPlusProperties.class)
public class AuthorityAutoConfiguration extends BaseDbConfiguration {
    /**
     * 数据源信息
     *
     * @return
     */
    @Bean(name = "druidDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource druid() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "authorityDataSource")
    public DataSource db1(@Qualifier("druidDataSource") DataSource dataSource) {
        return dataSource;
    }

    /**
     * 数据源事务管理器
     *
     * @return
     */
    @Bean(name = "txAuthority")
    public DataSourceTransactionManager rdsTransactionManager(@Qualifier("authorityDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * mybatis Sql Session 工厂
     *
     * @param globalConfig
     * @param myMetaObjectHandler
     * @return
     * @throws Exception
     */
    @Bean("authoritySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("authorityGlobalConfig") GlobalConfig globalConfig,
                                               @Qualifier("myMetaObjectHandler") MetaObjectHandler myMetaObjectHandler,
                                               @Qualifier("authorityDataSource") DataSource dataSource
    ) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        return super.setMybatisSqlSessionFactoryBean(sqlSessionFactory,
                new String[]{"classpath*:mapper_**/**/*Mapper.xml"},
                globalConfig, myMetaObjectHandler);
    }


    /**
     * 事务拦截器
     *
     * @param transactionManager
     * @return
     */
    @Bean("authorityTxAdvice")
    @Override
    public TransactionInterceptor txAdvice(@Qualifier("txAuthority") PlatformTransactionManager transactionManager) {
        return super.txAdvice(transactionManager);
    }

    /**
     * 事务 Advisor
     *
     * @param transactionManager
     * @return
     */
    @Bean("authorityTxAdviceAdvisor")
    @Override
    public Advisor txAdviceAdvisor(@Qualifier("txAuthority") PlatformTransactionManager transactionManager) {
        return super.txAdviceAdvisor(transactionManager);
    }


    /**
     * mybatis plus 全局配置
     *
     * @return
     */
    @Bean("authorityGlobalConfig")
    public GlobalConfig globalConfig() {
        return defGlobalConfig();
    }

    /**
     * 日志记录
     *
     * @return
     */
    @Override
    protected Logger getLog() {
        return log;
    }

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Override
    public DataScopeInterceptor getDataScopeInterceptor() {
        return new DataScopeInterceptor((userId) -> SpringUtils.getBean(UserService.class).getDataScopeById(userId));
    }
}
