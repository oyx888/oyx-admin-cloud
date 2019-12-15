package com.github.oyx.auth.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.github.oyx.auth.server.properties.AuthServerProperties.PREFIX;

/**
 * @author OYX
 * @date 2019-12-15 14:17
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = PREFIX)
public class AuthServerProperties {
    public static final String PREFIX = "authentication";

    private TokenInfo user;

    @Data
    public static class TokenInfo {
        /**
         * 过期时间
         */
        private Integer expire = 7200;
        /**
         * 加密 服务使用
         */
        private String priKey;
        /**
         * 解密
         */
        private String pubKey;
    }
}
