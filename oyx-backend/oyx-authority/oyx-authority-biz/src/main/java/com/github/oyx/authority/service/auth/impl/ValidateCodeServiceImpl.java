package com.github.oyx.authority.service.auth.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.github.oyx.authority.service.auth.ValidateCodeService;
import com.github.oyx.cache.repository.CacheRepository;
import com.github.oyx.common.constant.CacheKey;
import com.github.oyx.exception.BizException;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;

/**
 * @author OYX
 * @date 2019-12-16 11:02
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private CacheRepository cache;

    @Override
    public void create(String key, HttpServletResponse response) throws IOException {
        if(StringUtils.isBlank(key)){
            throw BizException.validFail("验证码key不能为空");
        }
        setHeader(response, "arithmetic");
        Captcha captcha = createCaptcha("arithmetic");
        cache.setExpire(CacheKey.CAPTCHA + key, StringUtils.lowerCase(captcha.text()), 60L);
        captcha.out(response.getOutputStream());
    }

    private void setHeader(HttpServletResponse response, String type){
        if(StringUtils.equalsIgnoreCase(type, "gif")){
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }

    @Override
    public boolean check(String key, String value) {
        if (StringUtils.isBlank(value)) {
            throw BizException.validFail("请输入验证码");
        }
        String cacheObject = cache.get(CacheKey.CAPTCHA + key);
        if (cacheObject == null) {
            throw BizException.validFail("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(cacheObject))) {
            throw BizException.validFail("验证码不正确");
        }
        cache.del(CacheKey.CAPTCHA + key);
        return true;
    }

    private Captcha createCaptcha(String type) {
        Captcha captcha = null;
        if(StringUtils.equalsIgnoreCase(type, "gif")){
            captcha = new GifCaptcha(115, 42, 4);
        } else if (StringUtils.equalsIgnoreCase(type, "png")) {
            captcha = new SpecCaptcha(115, 42, 4);
        } else if (StringUtils.equalsIgnoreCase(type, "arithmetic")) {
            captcha = new ArithmeticCaptcha(115, 42);
        } else if (StringUtils.equalsIgnoreCase(type, "chinese")) {
            captcha = new ChineseCaptcha(115, 42);
        }
        captcha.setCharType(2);
        return captcha;
    }
}
