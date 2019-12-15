package com.github.oyx.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.oyx.base.R;
import com.github.oyx.user.feign.fallback.UserResolveApiFallback;
import com.github.oyx.user.model.SysUser;

/**
 * 用户操作API
 *
 * @author oyx
 * @date 2019/07/10
 */
@FeignClient(name = "${oyx.feign.authority-server:oyx-authority-server}", fallbackFactory = UserResolveApiFallback.class)
public interface UserResolveApi {

    /**
     * 根据id 查询用户详情
     *
     * @param id
     * @param userQuery
     * @return
     */
    @PostMapping(value = "/user/anno/id/{id}")
    R<SysUser> getById(@PathVariable("id") Long id, @RequestBody UserQuery userQuery);
}
