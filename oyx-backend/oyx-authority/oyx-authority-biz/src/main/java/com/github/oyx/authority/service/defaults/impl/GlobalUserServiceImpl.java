package com.github.oyx.authority.service.defaults.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.oyx.authority.dao.defaults.GlobalUserMapper;
import com.github.oyx.authority.dto.defaults.GlobalUserSaveDTO;
import com.github.oyx.authority.dto.defaults.GlobalUserUpdateDTO;
import com.github.oyx.authority.entity.auth.User;
import com.github.oyx.authority.entity.defaults.GlobalUser;
import com.github.oyx.authority.service.auth.UserService;
import com.github.oyx.authority.service.defaults.GlobalUserService;
import com.github.oyx.context.BaseContextHandler;
import com.github.oyx.database.mybatis.conditions.Wraps;
import com.github.oyx.dozer.DozerUtils;
import com.github.oyx.utils.BizAssert;
import com.github.oyx.utils.StrHelper;

import cn.hutool.core.util.StrUtil;

/**
 * @author OYX
 * @date 2019-12-16 14:10
 */
@Service
public class GlobalUserServiceImpl extends ServiceImpl<GlobalUserMapper, GlobalUser> implements GlobalUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private DozerUtils dozer;

    @Override
    public Boolean check(String tenantCode, String account) {
        int globalUserCount = super.count(Wraps.<GlobalUser>lbQ()
                .eq(GlobalUser::getTenantCode, tenantCode)
                .eq(GlobalUser::getAccount, account));
        if (globalUserCount > 0) {
            return false;
        }
        BaseContextHandler.setTenant(tenantCode);
        int userCount = userService.count(Wraps.<User>lbQ()
                .eq(User::getAccount, account));
        if (userCount > 0) {
            return false;
        }
        return true;
    }

    @Override
    public GlobalUser save(GlobalUserSaveDTO globalUserSaveDTO) {
        BizAssert.equals(globalUserSaveDTO.getPassword(), globalUserSaveDTO.getConfirmPassword(), "2次输入的密码不一致");
        String md5Password = DigestUtils.md5Hex(globalUserSaveDTO.getPassword());
        GlobalUser globalAccount = dozer.map(globalUserSaveDTO, GlobalUser.class);
        // defaults 库
        globalAccount.setPassword(md5Password);
        save(globalAccount);
        // 保存到租户库
        BaseContextHandler.setTenant(globalUserSaveDTO.getTenantCode());
        // 1，保存租户用户 // 租户库
        User user = dozer.map(globalUserSaveDTO, User.class);
        user.setId(globalAccount.getId());
        user.setPassword(md5Password)
                .setName(StrHelper.getOrDef(globalUserSaveDTO.getName(), globalUserSaveDTO.getAccount()))
                .setStatus(true);
        userService.save(user);
        return globalAccount;
    }

    @Override
    public GlobalUser update(GlobalUserUpdateDTO data) {
        if (StrUtil.isNotBlank(data.getPassword()) || StrUtil.isNotBlank(data.getPassword())) {
            BizAssert.equals(data.getPassword(), data.getConfirmPassword(), "2次输入的密码不一致");
        }

        GlobalUser globalUser = dozer.map(data, GlobalUser.class);
        User user = dozer.map(data, User.class);
        if (StrUtil.isNotBlank(data.getPassword())) {
            String md5Password = DigestUtils.md5Hex(data.getPassword());
            globalUser.setPassword(md5Password);

            user.setPassword(md5Password);
        }
        updateById(globalUser);
        BaseContextHandler.setTenant(data.getTenantCode());
        userService.updateById(user);
        return globalUser;
    }

    @Override
    public void removeByIds(String tenantCode, Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        super.removeByIds(idList);

        BaseContextHandler.setTenant(tenantCode);
        userService.removeByIds(idList);
        //TODO 缓存 & T人下线
    }
}
