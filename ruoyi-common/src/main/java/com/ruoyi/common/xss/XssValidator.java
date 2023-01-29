package com.ruoyi.common.xss;

import com.ruoyi.common.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义xss校验注解实现
 *
 * @author ruoyi
 */
public class XssValidator implements ConstraintValidator<Xss, String> {

    /**
     * html字符串值
     */
    private String HTML_PATTERN;

    @Override
    public void initialize(Xss constraintAnnotation) {
        this.HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        return !containsHtml(value);
    }

    /**
     * 验证字符串是否支持HTML
     * @param value 字符串值
     * @return boolean
     */
    private boolean containsHtml(String value) {
        Pattern pattern = Pattern.compile(HTML_PATTERN);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

}