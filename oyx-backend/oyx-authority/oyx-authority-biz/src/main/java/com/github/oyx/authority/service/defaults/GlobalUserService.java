package com.github.oyx.authority.service.defaults;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.oyx.authority.dto.defaults.GlobalUserSaveDTO;
import com.github.oyx.authority.dto.defaults.GlobalUserUpdateDTO;
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

    /**
     * 新建用户
     * @param globalUserSaveDTO
     * @return
     */
    GlobalUser save(GlobalUserSaveDTO globalUserSaveDTO);

    /**
     * 修改
     *
     * @param data
     * @return
     */
    GlobalUser update(GlobalUserUpdateDTO data);

    /**
     * 删除全局用户
     *
     * @param ids
     */
    void removeByIds(String tenantCode, Long[] ids);
}
