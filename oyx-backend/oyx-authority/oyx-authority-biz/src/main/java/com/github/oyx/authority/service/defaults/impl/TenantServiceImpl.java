package com.github.oyx.authority.service.defaults.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.oyx.authority.dao.defaults.TenantMapper;
import com.github.oyx.authority.entity.defaults.Tenant;
import com.github.oyx.authority.service.defaults.TenantService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 企业
 * </p>
 *
 * @author oyx
 * @date 2019-12-19
 */
@Slf4j
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

}
