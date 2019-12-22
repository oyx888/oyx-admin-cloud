package com.github.oyx.authority.service.auth.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.oyx.auth.server.utils.JwtTokenServerUtils;
import com.github.oyx.auth.utils.JwtUserInfo;
import com.github.oyx.auth.utils.Token;
import com.github.oyx.authority.dto.auth.LoginDTO;
import com.github.oyx.authority.dto.auth.UserDTO;
import com.github.oyx.authority.enumeration.auth.Sex;
import com.github.oyx.authority.entity.defaults.GlobalUser;
import com.github.oyx.authority.service.auth.UserService;
import com.github.oyx.authority.service.defaults.GlobalUserService;
import com.github.oyx.base.R;
import com.github.oyx.dozer.DozerUtils;
import com.github.oyx.exception.BizException;
import com.github.oyx.exception.code.ExceptionCode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author OYX
 * @date 2019-12-16 13:56
 */
@Slf4j
@Service
public class AuthManager {

    private static final String SUPER_TENANT = "admin";

    @Autowired
    private JwtTokenServerUtils jwtTokenServerUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private DozerUtils dozer;

    @Autowired
    private GlobalUserService globalUserService;

    public R<LoginDTO> adminLogin(String account, String password){
        GlobalUser user = globalUserService.getOne(Wrappers.<GlobalUser>lambdaQuery()
        .eq(GlobalUser::getAccount, account).eq(GlobalUser::getTenantCode, SUPER_TENANT));
        //密码错误
        if(user == null){
            throw new BizException(ExceptionCode.JWT_USER_INVALID.getCode(), ExceptionCode.JWT_USER_INVALID.getMsg());
        }
        String passwordMd5 = DigestUtils.md5Hex(password);
        if(!user.getPassword().equalsIgnoreCase(passwordMd5)){
            userService.updatePasswordErrorNumById(user.getId());
            return R.fail("用户名或密码错误!");
        }
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName(), 0L, 0L);

        Token token = jwtTokenServerUtils.generateUserToken(userInfo, null);
        log.info("token={}", token.getToken());

        UserDTO dto = dozer.map(user, UserDTO.class);
        dto.setStatus(true).setOrgId(0L).setStationId(0L).setAvatar("").setSex(Sex.M).setWorkDescribe("心情很美丽");
        return R.success(LoginDTO.builder().user(dto).token(token).build());
    }
}
