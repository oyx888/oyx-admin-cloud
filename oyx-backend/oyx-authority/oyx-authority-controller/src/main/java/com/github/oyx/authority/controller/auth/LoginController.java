package com.github.oyx.authority.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.oyx.authority.dto.auth.LoginDTO;
import com.github.oyx.authority.service.auth.ValidateCodeService;
import com.github.oyx.authority.service.auth.impl.AuthManager;
import com.github.oyx.base.BaseController;
import com.github.oyx.base.R;
import com.github.oyx.context.BaseContextHandler;
import com.github.oyx.log.annotation.SysLog;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 登陆
 */
@RestController
@RequestMapping("/anno")
@Api(value = "UserAuthController", tags = "登录")
@Slf4j
public class LoginController extends BaseController {
    @Autowired
    private AuthManager authManager;

    @Autowired
    private ValidateCodeService validateCodeService;

    @ApiOperation(value = "超级管理员登陆", notes = "超级管理员登陆")
    @PostMapping("/admin/login")
    @SysLog("管理员登陆")
    public R<LoginDTO> loginAdminTx(@RequestParam(value = "key", required = false) String key,
                                    @RequestParam(value = "code", required = false) String code,
                                    @RequestParam(value = "tenant", required = false) String tenant,
                                    @RequestParam(value = "account") String account,
                                    @RequestParam(value = "password") String password){
        log.info("account={}", account);
        if(StrUtil.isEmpty(tenant)){
            tenant = BaseContextHandler.getTenant();
        }
        if(validateCodeService.check(key, code)){
            return authManager.adminLogin(account, password);
        }
        return success(null);
    }
}
