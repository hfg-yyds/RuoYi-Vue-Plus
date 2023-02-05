package com.ruoyi.common.utils.sign;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import lombok.extern.slf4j.Slf4j;

/**
 * Md5加密方法
 *
 * @author ruoyi
 */
@Slf4j
public class Md5Utils {

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes(StandardCharsets.UTF_8));
            return algorithm.digest();
        } catch (Exception e) {
            log.error("MD5 Error...", e);
        }
        return null;
    }

    private static String toHex(byte[] hash) {
        if (hash == null) {
            return null;
        }
        StringBuilder buf = new StringBuilder(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * 获取字符串的Hash索引
     * @param s 字符串
     * @return hash值
     */
    public static String hash(String s) {
        try {
            return toHex(md5(s));
        } catch (Exception e) {
            log.error("not supported charset...{},堆栈信息:{}", e.getMessage(),e);
            return s;
        }
    }

}
