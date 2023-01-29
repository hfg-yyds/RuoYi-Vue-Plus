package com.ruoyi.common.utils;

import java.util.function.Supplier;

/**
 * <p>
 *  断言
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-01-28
 */
public class Asserts {

    /**
     * 断言不能为NULL
     * @param object 值
     * @param errorSupplier 异常
     * @param <X> 异常
     * @throws X X
     */
    public static <X extends Throwable> void isNotNull(Object object, Supplier<X> errorSupplier) throws X {
        if (null == object) {
            throw errorSupplier.get();
        }
    }

    /**
     * 断言是True
     * @param value 值
     * @param errorSupplier e
     * @param <X> x
     * @throws X x
     */
    public static <X extends Throwable> void isTrue(Boolean value,Supplier<X> errorSupplier) throws X {
        if (Boolean.FALSE.equals(value)) {
            throw errorSupplier.get();
        }
    }

    /**
     * 断言是False
     * @param value 值
     * @param errorSupplier e
     * @param <X> e
     * @throws X e
     */
    public static <X extends Throwable> void isFalse(Boolean value,Supplier<X> errorSupplier) throws X {
        if (Boolean.TRUE.equals(value)) {
            throw errorSupplier.get();
        }
    }

}
