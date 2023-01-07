package com.ruoyi.common.interfaces;

/**
 * <p>
 *
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-01-07
 */
@FunctionalInterface
public interface RunnableAndGetResult<P> {
    P run() throws Throwable;
}

