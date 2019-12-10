package com.github.oyx.base;

import java.util.Arrays;
import java.util.Map;

import com.github.oyx.utils.MapHelper;

/**
 * @author OYX
 * @date 2019-12-10 14:00
 */
public interface BaseEnum {

    static Map<String, String> getMap(BaseEnum[] list){
        return MapHelper.uniqueIndex(Arrays.asList(list), BaseEnum::getCode, BaseEnum::getDesc);
    }

    /**
     * 编码重写
     *
     * @return
     */
    default String getCode() {
        return toString();
    }

    /**
     * 描述
     *
     * @return
     */
    String getDesc();
}
