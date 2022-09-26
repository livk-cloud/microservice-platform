package com.livk.common.core.function;

import java.util.function.Predicate;

/**
 * <p>
 * BranchHandle
 * </p>
 *
 * @author livk
 * @date 2022/09/11
 */
@FunctionalInterface
public interface BranchHandle {

    /**
     * Is true or false branch handle.
     *
     * @param <T>       the type parameter
     * @param predicate the predicate
     * @return the branch handle
     */
    static <T> BranchHandle isTrueOrFalse(Predicate<T> predicate) {
        return (trueHandle, falseHandle) -> {
            if (predicate.test(null))
                trueHandle.run();
            else
                falseHandle.run();
        };
    }

    /**
     * True or false handle.
     *
     * @param trueHandle  the true handle
     * @param falseHandle the false handle
     */
    void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);

}
