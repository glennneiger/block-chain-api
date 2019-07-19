package com.hb56.common.result;

/**
 * @author Been
 * @date 2018/9/28
 */
public class RestUtil {

    public static <T> RestInfo<T> setData(T t) {
        return setSuccessData(t, null);
    }

    public static <T> RestInfo<T> setSuccessMsg(String msg) {
        return setSuccessData(null, msg);
    }

    public static <T> RestInfo<T> setSuccessData(T t, String msg) {
        RestInfo<T> result = new RestInfo<>();
        result.setSuccess(true);
        result.setResult(t);
        result.setCode(200);
        result.setMessage(msg);
        return result;
    }

    public static <T> RestInfo<T> setErrorMsg(String msg) {
        return setErrorAll(500, msg, null);
    }

    public static <T> RestInfo<T> setErrorMsg(T t, String msg) {
        return setErrorAll(500, msg, t);
    }

    public static <T> RestInfo<T> setErrorCode(Integer code, String msg) {
        return setErrorAll(code, msg, null);
    }

    public static <T> RestInfo<T> setErrorAll(Integer code, String msg, T t) {
        RestInfo<T> result = new RestInfo<>();
        result.setSuccess(false);
        result.setMessage(msg);
        result.setCode(code);
        result.setResult(t);
        return result;
    }

}
