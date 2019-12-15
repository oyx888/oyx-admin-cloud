package com.github.oyx.auth.server.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.github.oyx.auth.server.properties.AuthServerProperties;
import com.github.oyx.auth.server.utils.JwtTokenServerUtils;

/**
 * @author OYX
 * @date 2019-12-15 14:17
 */
@EnableConfigurationProperties(value = {
        AuthServerProperties.class,
})
public class AuthServerConfiguration {
    @Bean
    public JwtTokenServerUtils getJwtTokenServerUtils(AuthServerProperties authServerProperties) {
        return new JwtTokenServerUtils(authServerProperties);
    }
}
