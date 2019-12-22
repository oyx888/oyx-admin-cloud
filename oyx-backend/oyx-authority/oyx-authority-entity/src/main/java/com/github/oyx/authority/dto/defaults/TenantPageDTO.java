package com.github.oyx.authority.dto.defaults;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author OYX
 * @date 2019-12-22 14:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TenantPageDTO", description = "企业")
public class TenantPageDTO extends TenantSaveDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建开始时间")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "创建截止时间")
    private LocalDateTime endCreateTime;
}
