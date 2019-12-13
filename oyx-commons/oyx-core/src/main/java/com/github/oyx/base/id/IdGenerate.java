package com.github.oyx.base.id;

import java.io.Serializable;

/**
 * 主键id生成器
 * @author OYX
 * @param <T>
 */
@FunctionalInterface
public interface IdGenerate <T extends Serializable> {
    /**
     * id生成器
     * @return
     */
    T generate();
}
