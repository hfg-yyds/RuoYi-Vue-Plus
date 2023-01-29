package com.ruoyi.common.utils.bean;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 * bean对象属性验证
 *
 * @author ruoyi
 */
public class BeanValidators {

    /**
     * 属性校验器
     * @param validator validator
     * @param object 对象
     * @param groups 校验组
     * @throws ConstraintViolationException e
     */
    public static void validateWithException(Validator validator, Object object, Class<?>... groups)
            throws ConstraintViolationException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

}
