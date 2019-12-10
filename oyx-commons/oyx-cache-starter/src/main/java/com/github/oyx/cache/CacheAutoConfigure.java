package com.github.oyx.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.github.oyx.utils.StrPool;

import lombok.extern.slf4j.Slf4j;

/**
 * @author OYX
 * @date 2019-12-11 6:21
 */
@Slf4j
@EnableCaching
@Import(RedisAutoConfigure.class)
public class CacheAutoConfigure {
    /**
     * key 的生成
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(StrPool.COLON);
            sb.append(method.getName());
            for (Object obj : objects) {
                if (obj != null) {
                    sb.append(StrPool.COLON);
                    sb.append(obj.toString());
                }
            }
            return sb.toString();
        };
    }
}
