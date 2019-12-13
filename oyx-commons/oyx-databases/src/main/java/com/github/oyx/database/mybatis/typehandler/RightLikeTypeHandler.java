package com.github.oyx.database.mybatis.typehandler;


import org.apache.ibatis.type.Alias;

/**
 * 仅仅用于like查询
 *
 * @author oyx
 */
@Alias("rightLike")
public class RightLikeTypeHandler extends BaseLikeTypeHandler {
    public RightLikeTypeHandler() {
        super(false, true);
    }
}

