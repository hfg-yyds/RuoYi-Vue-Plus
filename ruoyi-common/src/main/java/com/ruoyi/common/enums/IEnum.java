package com.ruoyi.common.enums;

/**
 * <p>
 *  获取的IEnum
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-01-08
 */
public interface IEnum<T> {
    /**
     * 返回获取的值
     * @return T
     */
    T value();
}
