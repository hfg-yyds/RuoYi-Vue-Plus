package com.ruoyi.common.constant;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 *
 * @author ruoyi
 */
public interface Constants {
    /**
     * UTF-8 字符集
     */
    String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    String GBK = "GBK";

    /**
     * www主域
     */
    String WWW = "www.";

    /**
     * http请求
     */
    String HTTP = "http://";

    /**
     * https请求
     */
    String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    String FAIL = "1";

    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    String LOGOUT = "Logout";

    /**
     * 注册
     */
    String REGISTER = "Register";

    /**
     * 登录失败
     */
    String LOGIN_FAIL = "Error";

    /**
     * 验证码有效期（分钟）
     */
    Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    String TOKEN = "token";

    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    String JWT_USERNAME = Claims.SUBJECT;

    /**
     * 用户头像
     */
    String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    String JWT_AUTHORITIES = "authorities";

    /**
     * 资源映射路径 前缀
     */
    String RESOURCE_PREFIX = "/profile";

    /**
     * RMI 远程方法调用
     */
    String LOOKUP_RMI = "rmi:";

    /**
     * LDAP 远程方法调用
     */
    String LOOKUP_LDAP = "ldap:";

    /**
     * LDAPS 远程方法调用
     */
    String LOOKUP_LDAPS = "ldaps:";

    /**
     * 定时任务白名单配置（仅允许访问的包名，如其他需要可以自行添加）
     */
    String[] JOB_WHITELIST_STR = {"com.ruoyi"};

    /**
     * 定时任务违规的字符
     */
    String[] JOB_ERROR_STR = {"java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
            "org.springframework", "org.apache", "com.ruoyi.common.utils.file", "com.ruoyi.common.config"};
}
