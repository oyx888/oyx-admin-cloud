package com.github.oyx.authority.service.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ValidateCodeService {

    /**
     * 生成验证码
     *
     * @param key      验证码 uuid
     * @param response HttpServletResponse
     * @throws IOException
     */
    void create(String key, HttpServletResponse response) throws IOException;

    /**
     * 校验验证码
     *
     * @param key   前端上送 key
     * @param value 前端上送待校验值
     */
    boolean check(String key, String value);
}
