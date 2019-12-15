package com.github.oyx.auth.client.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.github.oyx.auth.client.properties.AuthClientProperties;
import com.github.oyx.auth.client.utils.JwtTokenClientUtils;

/**
 * @author OYX
 * @date 2019-12-13 17:49
 */
@EnableConfigurationProperties(value = {
        AuthClientProperties.class
})
public class AuthClientConfiguration {
    public JwtTokenClientUtils getJwtTokenClientUtils(AuthClientProperties authClientProperties){
        return new JwtTokenClientUtils(authClientProperties);
    }
}
