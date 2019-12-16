package com.github.oyx.authority.dto.auth;

import java.util.List;

import com.github.oyx.auth.utils.Token;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author OYX
 * @date 2019-12-16 10:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "LoginDTO", description = "登录信息")
public class LoginDTO {
    private UserDTO user;

    private Token token;

    private List<String> permissionsList;
}
