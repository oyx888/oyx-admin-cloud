package com.github.oyx.function;

/**
 * @author OYX
 * @date 2019-12-10 15:52:30
 */
public interface CheckedFunction<T, R> {
    /**
     * 执行
     *
     * @param t 入参
     * @return R 出参
     * @throws Exception
     */
    R apply(T t) throws Exception;
}
