package com.github.oyx.generator.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 父类实体类型
 *
 * @author oyx
 * @date 2019/05/14
 */
@Getter
@AllArgsConstructor
public enum EntityType {
    /**
     * 只有id
     */
    SUPER_ENTITY("com.github.oyx.base.entity.SuperEntity", new String[]{"id", "create_time", "create_user"}),
    /**
     * 有创建人创建时间等
     */
    ENTITY("com.github.oyx.base.entity.Entity", new String[]{"id", "create_time", "create_user", "update_time", "update_user"}),
    ;

    private String val;
    private String[] columns;


    public boolean eq(String val) {
        if (this.name().equals(val)) {
            return true;
        }
        return false;
    }

    public boolean eq(EntityType val) {
        if (val == null) {
            return false;
        }
        return eq(val.name());
    }

}
