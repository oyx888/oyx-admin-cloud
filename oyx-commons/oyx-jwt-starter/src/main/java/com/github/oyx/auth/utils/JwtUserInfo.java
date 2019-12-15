package com.github.oyx.auth.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author OYX
 * @date 2019-12-13 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfo {
    /**
     * 账号id
     */
    private Long userId;
    /**
     * 账号
     */
    private String account;
    /**
     * 姓名
     */
    private String name;

    /**
     * 当前登录人单位id
     */
    private Long orgId;

    /**
     * 当前登录人岗位ID
     */
    private Long stationId;
}
