package com.github.oyx.exception.code;

/**
 * @author OYX
 * @date 2019-12-10 14:37
 */
public interface BaseExceptionCode {

    /**
     * 异常编码
     */
    int getCode();

    /**
     * 异常消息
     */
    String getMsg();
}
