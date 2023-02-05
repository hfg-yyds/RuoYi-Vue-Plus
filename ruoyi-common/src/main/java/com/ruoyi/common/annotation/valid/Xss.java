package com.ruoyi.common.annotation.valid;

import com.ruoyi.common.xss.XssValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义xss校验注解
 *
 * @author ruoyi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Constraint(validatedBy = {XssValidator.class})
public @interface Xss {

    /**
     * 错误信息描述
     * @return 信息
     */
    String message() default "不允许任何脚本运行";
    /**
     * 分组校验
     * @return 分组
     */
    Class<?>[] groups() default {};
    /**
     * 重载
     * @return 类
     */
    Class<? extends Payload>[] payload() default {};

}
