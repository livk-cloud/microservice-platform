package com.livk.common.core.function;

import java.util.function.Predicate;

/**
 * <p>
 * ThrowException
 * </p>
 *
 * @author livk
 * @date 2021/12/11
 */
@FunctionalInterface
public interface ThrowException {

    /**
     * Is true throw exception.
     *
     * @param <T>       the type parameter
     * @param predicate the b
     * @return the throw exception
     */
    static <T> ThrowException isTrue(Predicate<T> predicate) {
        return t -> {
            if (predicate.test(null))
                throw t;
        };
    }

    /**
     * Throw exception.
     *
     * @param t the message
     */
    void throwException(Exception t) throws Exception;

}
