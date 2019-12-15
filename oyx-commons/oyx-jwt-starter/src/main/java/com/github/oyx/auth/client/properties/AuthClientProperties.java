package com.github.oyx.auth.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.github.oyx.auth.client.properties.AuthClientProperties.PREFIX;

/**
 * @author OYX
 * @date 2019-12-15 14:05
 */
@ConfigurationProperties(prefix = PREFIX)
@Data
@NoArgsConstructor
public class AuthClientProperties {

    public static final String PREFIX = "authentication";

    private TokeInfo user;

    @Data
    public static class TokeInfo{
        /**
         * 请求头名称
         */
        private String headerName;
        /**
         * 解密 网关使用
         */
        private String pubKey;
    }
}
