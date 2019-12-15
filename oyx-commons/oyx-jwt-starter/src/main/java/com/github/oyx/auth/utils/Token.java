package com.github.oyx.auth.utils;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author OYX
 * @date 2019-12-13 17:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {
    /**
     * token
     */
    private String token;
    /**
     * 有效时间：单位：秒
     */
    private Integer expire;
}
