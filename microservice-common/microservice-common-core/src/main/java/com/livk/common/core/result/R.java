package com.livk.common.core.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.livk.common.core.constant.CommonConstant;
import lombok.Getter;

/**
 * <p>
 * R
 * </p>
 *
 * @author livk
 * @date 2022/8/15
 */
@Getter
public class R<T> {

    private final int code;

    private final String msg;

    private final T data;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public R(@JsonProperty("code") int code,
             @JsonProperty("msg") String msg,
             @JsonProperty("data") T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

