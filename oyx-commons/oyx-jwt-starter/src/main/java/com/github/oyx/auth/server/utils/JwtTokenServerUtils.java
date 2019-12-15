package com.github.oyx.auth.server.utils;

import com.github.oyx.auth.server.properties.AuthServerProperties;
import com.github.oyx.auth.utils.JwtHelper;
import com.github.oyx.auth.utils.JwtUserInfo;
import com.github.oyx.auth.utils.Token;
import com.github.oyx.exception.BizException;

import lombok.AllArgsConstructor;

/**
 * @author OYX
 * @date 2019-12-15 14:19
 */
@AllArgsConstructor
public class JwtTokenServerUtils {
    /**
     * 认证服务端使用，如 authority-server
     * 生成和 解析token
     */
    private AuthServerProperties authServerProperties;

    /**
     * 生成token
     *
     * @param jwtInfo
     * @param expire
     * @return
     * @throws BizException
     */
    public Token generateUserToken(JwtUserInfo jwtInfo, Integer expire) throws BizException {
        AuthServerProperties.TokenInfo userTokenInfo = authServerProperties.getUser();
        if (expire == null || expire <= 0) {
            expire = userTokenInfo.getExpire();
        }
        return JwtHelper.generateUserToken(jwtInfo, userTokenInfo.getPriKey(), expire);
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws BizException
     */
    public JwtUserInfo getUserInfo(String token) throws BizException {
        AuthServerProperties.TokenInfo userTokenInfo = authServerProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }
}
