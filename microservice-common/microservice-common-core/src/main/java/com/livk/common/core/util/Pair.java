package com.livk.common.core.util;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * Pair
 * </p>
 *
 * @author livk
 * @date 2022/8/15
 */
public record Pair<K, V>(K key, V value) implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = -2303547536834226401L;

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<K, V> clone() {
        try {
            return (Pair<K, V>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
