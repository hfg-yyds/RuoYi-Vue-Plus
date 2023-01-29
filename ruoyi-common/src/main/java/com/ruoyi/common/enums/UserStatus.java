package com.ruoyi.common.enums;

import lombok.Getter;

/**
 * 用户状态
 *
 * @author ruoyi
 */
@Getter
public enum UserStatus {

    OK("0", "正常"),

    DISABLE("1", "停用"),

    DELETED("2", "删除");

    private final String code;

    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

}
