package com.ruoyi.common.web.domain;

import java.io.Serializable;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.interfaces.RunResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 响应信息主体
 *
 * @author ruoyi
 */
@Slf4j
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功
     */
    public static final int SUCCESS = HttpStatus.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = HttpStatus.ERROR;

    private int code;

    private String msg;

    private T data;

    public static <T> R<T> ok() {
        return restResult(null, SUCCESS, "操作成功");
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, SUCCESS, "操作成功");
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> R<T> fail() {
        return restResult(null, FAIL, "操作失败");
    }

    public static <T> R<T> fail(String msg) {
        return restResult(null, FAIL, msg);
    }

    public static <T> R<T> fail(T data) {
        return restResult(data, FAIL, "操作失败");
    }

    public static <T> R<T> fail(T data, String msg) {
        return restResult(data, FAIL, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return restResult(null, code, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Boolean isError(R<T> ret) {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(R<T> ret) {
        return R.SUCCESS == ret.getCode();
    }

    public static R<?> run(Runnable runnable) {
        try {
            runnable.run();
            return ok();
        } catch (Throwable e) {
            log.error("异常信息:" + e.getMessage());
            return fail(e);
        }
    }

    /**
     * @param runnable 有返回值得
     * @param <V>      v
     * @return v
     */
    public static <V> R<V> run(RunResult<V> runnable) {
        try {
            return ok(runnable.run());
        } catch (Throwable e) {
            log.error("异常信息:" + e.getMessage());
            return (R<V>) fail(e);
        }
    }

}
