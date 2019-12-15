package com.github.oyx.user.feign.fallback;

import org.springframework.stereotype.Component;

import com.github.oyx.base.R;
import com.github.oyx.user.feign.UserQuery;
import com.github.oyx.user.feign.UserResolveApi;
import com.github.oyx.user.model.SysUser;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户API熔断
 *
 * @author oyx
 * @date 2019/07/10
 */
@Component
@Slf4j
public class UserResolveApiFallback implements FallbackFactory<UserResolveApi> {


    @Override
    public UserResolveApi create(Throwable throwable) {
        return new UserResolveApi() {
            /**
             * 根据id 查询用户详情
             *
             * @param id
             * @param userQuery
             * @return
             */
            @Override
            public R<SysUser> getById(Long id, UserQuery userQuery) {
                log.error("通过用户名查询用户异常:{}", id, throwable);
                return R.timeout();
            }
        };
    }
}
