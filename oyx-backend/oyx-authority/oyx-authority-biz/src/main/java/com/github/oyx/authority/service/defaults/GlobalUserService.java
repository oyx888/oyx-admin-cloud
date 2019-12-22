package com.github.oyx.authority.service.defaults;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.oyx.authority.entity.defaults.GlobalUser;

/**
 * @author OYX
 * @date 2019-12-16 14:08
 */
public interface GlobalUserService extends IService<GlobalUser> {
    /**
     * 检测账号是否可用
     *
     * @param tenantCode
     * @param account
     * @return
     */
    Boolean check(String tenantCode, String account);
}
