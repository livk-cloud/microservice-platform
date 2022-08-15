package com.livk.common.core.util;

import lombok.experimental.UtilityClass;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <p>
 * ObjectUtils
 * </p>
 *
 * @author livk
 * @date 2022/8/15
 */
@UtilityClass
public class ObjectUtils extends org.springframework.util.ObjectUtils {

    @SafeVarargs
    public <T> boolean allChecked(Predicate<T> predicate, T... ts) {
        return Stream.of(ts).allMatch(predicate);
    }

    @SafeVarargs
    public <T> boolean anyChecked(Predicate<T> predicate, T... ts) {
        return Stream.of(ts).anyMatch(predicate);
    }

}
