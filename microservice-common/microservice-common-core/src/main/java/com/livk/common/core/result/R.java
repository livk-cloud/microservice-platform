package com.livk.common.core.result;

import com.livk.common.core.constant.CommonConstant;

/**
 * <p>
 * R
 * </p>
 *
 * @author livk
 */
public record R<T>(int code, String msg, T data) {

    public static R<Void> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(CommonConstant.SUCCESS_CODE, CommonConstant.SUCCESS_MSG, data);
    }

    public static R<Void> fail(String msg) {
        return new R<>(CommonConstant.SUCCESS_CODE, msg, null);
    }

}

