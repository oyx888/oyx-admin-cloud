package com.github.oyx.base.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author OYX
 * @date 2019-12-10 14:57
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class Entity<T> extends SuperEntity<T> {
    public static final String UPDATE_TIME = "updateTime";
    public static final String UPDATE_USER = "updateUser";

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    @ApiModelProperty(value = "最后修改人ID")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    protected T updateUser;

    public Entity(T id, LocalDateTime createTime, T createUser, LocalDateTime updateTime, T updateUser) {
        super(id, createTime, createUser);
        this.updateTime = updateTime;
        this.updateUser = updateUser;
    }

    public Entity() {
    }
}
