package com.ruoyi.common.enums;

import com.ruoyi.common.metadata.IEnum;
import com.ruoyi.common.metadata.StringListValues;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户状态
 *
 * @author ruoyi
 */
@Getter
public enum UserStatus implements IEnum<String>, StringListValues {

    OK("0", "正常"),

    DISABLE("1", "停用"),

    DELETED("2", "删除");

    private final String code;

    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    @Override
    public String value() {
        return this.code;
    }

    /**
     * 用户代码
     */
    private static final List<String> LIST = Arrays.stream(values()).map(UserStatus::getCode).collect(Collectors.toList());

    @Override
    public List<String> list() {
        return LIST;
    }

}
