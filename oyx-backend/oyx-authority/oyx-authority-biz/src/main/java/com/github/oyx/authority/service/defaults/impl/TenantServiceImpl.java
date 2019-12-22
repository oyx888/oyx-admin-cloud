package com.github.oyx.authority.service.defaults.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.oyx.authority.dao.defaults.TenantMapper;
import com.github.oyx.authority.dto.defaults.TenantSaveDTO;
import com.github.oyx.authority.dto.defaults.TenantSaveInitDto;
import com.github.oyx.authority.entity.auth.User;
import com.github.oyx.authority.entity.defaults.Tenant;
import com.github.oyx.authority.entity.defaults.GlobalUser;
import com.github.oyx.authority.enumeration.defaults.TenantStatusEnum;
import com.github.oyx.authority.enumeration.defaults.TenantTypeEnum;
import com.github.oyx.authority.service.auth.UserService;
import com.github.oyx.authority.service.defaults.GlobalUserService;
import com.github.oyx.authority.service.defaults.InitSystemService;
import com.github.oyx.authority.service.defaults.TenantService;
import com.github.oyx.context.BaseContextHandler;
import com.github.oyx.database.mybatis.conditions.Wraps;
import com.github.oyx.dozer.DozerUtils;
import com.github.oyx.utils.BizAssert;
import com.github.oyx.utils.StrHelper;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;

    @Autowired
    private GlobalUserService globalUserService;

    @Autowired
    private DozerUtils dozer;

    @Autowired
    private InitSystemService initSystemService;

    @Override
    public boolean check(String tenantCode) {
        return super.count(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)) > 0;
    }

    /**
     * 快速初始化 暂未完成
     * @param data
     * @return
     */
//    @Override
//    public Tenant saveInit(TenantSaveInitDto data) {
//        BizAssert.equals(data.getPassword(), data.getConfirmPassword(), "2次输入得密码不一致");
//        BizAssert.isFalse(check(data.getCode()), "编码重复，请重新输入");
//
//        //1.保存租户（本地库）
//        Tenant tenant = dozer.map(data, Tenant.class);
//        tenant.setStatus(TenantStatusEnum.NORMAL);
//        tenant.setType(TenantTypeEnum.CREATE);
//        super.save(tenant);
//
//        //2保存全局数据库（本地库）
//        GlobalUser globalAccount = dozer.map(data, GlobalUser.class);
//        globalAccount.setTenantCode(tenant.getCode());
//        // defaults 库
//        globalUserService.save(globalAccount);
//
//        //3初始化库，表，数据 考虑异步完成
//        initSystemService.init(tenant.getCode());
//        //租户库
////        BaseContextHandler.setTenant(data.getCode());
////        BizAssert.isFalse(userService.check(data.getAccount()), "账号已经存在");
//        // 4，保存租户用户 // 租户库
//        User user = dozer.map(data, User.class);
//        user.setId(globalAccount.getId());
//        user.setPassword(DigestUtils.md5Hex(data.getPassword()));
////            user.setPasswordExpireTime(LocalDateTime.now().plusDays(authorityServerProperties.getPasswordExpire()));
//        user.setName(StrHelper.getOrDef(data.getName(), data.getAccount()));
//        userService.save(user);
//        return tenant;
//    }

    @Override
    public Tenant save(TenantSaveDTO data) {
        // defaults 库
        BizAssert.isFalse(check(data.getCode()), "编码重复，请重新输入");

        // 1， 保存租户 (默认库)
        Tenant tenant = dozer.map(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.NORMAL);
        tenant.setType(TenantTypeEnum.CREATE);
        // defaults 库
        super.save(tenant);

        // 3, 初始化库，表, 数据  考虑异步完成 // 租户库
        initSystemService.init(tenant.getCode());
        return tenant;
    }
}
