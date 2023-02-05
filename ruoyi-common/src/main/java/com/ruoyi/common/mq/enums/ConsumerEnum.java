package com.ruoyi.common.mq.enums;

import com.ruoyi.common.enums.IEnum;

/**
 * <p>
 *
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-04
 */
public enum ConsumerEnum implements IEnum<String> {

    TEST("test", "","测试");

    private final String code;

    private final String serviceImpl;

    private final String desc;

    ConsumerEnum(String code, String serviceImpl, String desc) {
        this.code = code;
        this.serviceImpl = serviceImpl;
        this.desc = desc;
    }

    @Override
    public String value() {
        return this.code;
    }

}
