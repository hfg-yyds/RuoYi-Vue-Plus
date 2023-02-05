package com.ruoyi.common.mq.enums;

import com.ruoyi.common.enums.IEnum;

/**
 * <p>
 *  发送者
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-03
 */
public enum ProducerEnum implements IEnum<String> {

    TEST("test","测试");

    private final String code;

    private final String desc;

    ProducerEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String value() {
        return this.code;
    }

}
