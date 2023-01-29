package com.ruoyi.common.interfaces;

/**
 * <p>
 *  有返回结果的函数式表达式
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-01-07
 */
@FunctionalInterface
public interface RunResult<T> {
    T run() throws Throwable;
}
