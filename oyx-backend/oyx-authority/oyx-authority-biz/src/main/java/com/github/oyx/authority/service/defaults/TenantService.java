package com.github.oyx.authority.service.defaults;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.oyx.authority.dto.defaults.TenantSaveDTO;
import com.github.oyx.authority.dto.defaults.TenantSaveInitDto;
import com.github.oyx.authority.entity.defaults.Tenant;

/**
 * <p>
 * 业务接口
 * 企业
 * </p>
 *
 * @author oyx
 * @date 2019-12-19
 */
public interface TenantService extends IService<Tenant> {
    /**
     * 检测机构是否存在
     * @param tenantCode
     * @return
     */
    boolean check (String tenantCode);

    /**
     * 机构初始化
     * @param tenantSaveInitDto
     * @return
     */
//    Tenant saveInit (TenantSaveInitDto data);

    Tenant save (TenantSaveDTO data);
}
