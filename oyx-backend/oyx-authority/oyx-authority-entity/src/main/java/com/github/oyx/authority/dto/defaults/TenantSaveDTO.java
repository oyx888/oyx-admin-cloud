package com.github.oyx.authority.dto.defaults;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.github.oyx.base.entity.Entity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.github.oyx.authority.enumeration.defaults.TenantTypeEnum;
import com.github.oyx.authority.enumeration.defaults.TenantStatusEnum;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 企业
 * </p>
 *
 * @author oyx
 * @since 2019-12-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "TenantSaveDTO", description = "企业")
public class TenantSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    @Length(max = 20, message = "企业编码长度不能超过20")
    private String code;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    @Length(max = 255, message = "企业名称长度不能超过255")
    private String name;
    /**
     * 类型
     * #{CREATE:创建;REGISTER:注册}
     */
    @ApiModelProperty(value = "类型")
    private TenantTypeEnum type;
    /**
     * 状态
     * #{NORMAL:正常;FORBIDDEN:禁用;WAITING:待审核;REFUSE:拒绝}
     */
    @ApiModelProperty(value = "状态")
    private TenantStatusEnum status;
    /**
     * 是否内置
     */
    @ApiModelProperty(value = "是否内置")
    private Boolean readonly;
    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人")
    @Length(max = 50, message = "责任人长度不能超过50")
    private String duty;
    /**
     * 有效期
     * 为空表示永久
     */
    @ApiModelProperty(value = "有效期")
    private LocalDateTime expirationTime;
    /**
     * logo地址
     */
    @ApiModelProperty(value = "logo地址")
    @Length(max = 255, message = "logo地址长度不能超过255")
    private String logo;
    /**
     * 企业简介
     */
    @ApiModelProperty(value = "企业简介")
    @Length(max = 255, message = "企业简介长度不能超过255")
    private String describe;
    /**
     * 用户密码有效期
     * 单位：天 0表示永久有效
     * 
     */
    @ApiModelProperty(value = "用户密码有效期")
    private Integer passwordExpire;
    /**
     * 是否多地登录
     */
    @ApiModelProperty(value = "是否多地登录")
    private Boolean isMultipleLogin;
    /**
     * 密码输错次数
     * 密码输错锁定账号的次数
     * 单位：次
     * 
     */
    @ApiModelProperty(value = "密码输错次数")
    private Integer passwordErrorNum;
    /**
     * 账号锁定时间
     * 密码输错${passwordErrorNum}次后，锁定账号的时间
     * 单位： h | d | w | m
     * 单位： 时 | 天 | 周 | 月
     * 如：0=当天晚上24点 2h = 2小时   2d = 2天  
     */
    @ApiModelProperty(value = "账号锁定时间")
    @Length(max = 50, message = "账号锁定时间长度不能超过50")
    private String passwordErrorLockTime;

}
