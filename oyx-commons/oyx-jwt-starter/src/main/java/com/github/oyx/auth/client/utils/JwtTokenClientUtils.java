package com.github.oyx.auth.client.utils;

import com.github.oyx.auth.client.properties.AuthClientProperties;
import com.github.oyx.auth.utils.JwtHelper;
import com.github.oyx.auth.utils.JwtUserInfo;

import lombok.AllArgsConstructor;

/**
 * @author OYX
 * @date 2019-12-15 14:07
 */
@AllArgsConstructor
public class JwtTokenClientUtils {

    private AuthClientProperties authClientProperties;

    public JwtUserInfo getUserInfo(String token){
        AuthClientProperties.TokeInfo userTokenInfo = authClientProperties.getUser();
        return JwtHelper.getJwtFromToken(token, userTokenInfo.getPubKey());
    }
}
