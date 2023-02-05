package com.ruoyi.common.meta;

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
    /**
     * 有返回结果的函数表达式
     * @return T
     * @throws Throwable e
     */
    T run() throws Throwable;
}
