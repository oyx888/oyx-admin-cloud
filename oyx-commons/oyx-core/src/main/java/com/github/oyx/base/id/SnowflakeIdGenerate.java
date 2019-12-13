package com.github.oyx.base.id;

/**
 * @author OYX
 * @date 2019-12-13 9:37
 */
public class SnowflakeIdGenerate extends AbstractIdGenerate<Long> {
    public SnowflakeIdGenerate(final long machineCode) {
        super(machineCode);
    }

    @Override
    public Long generate() {
        return super.generateLong();
    }
}
