package com.livk.common.core.http.annotation;

import com.livk.common.core.http.WebClientImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * EnableWebClient
 * </p>
 *
 * @author livk
 * @date 2022/9/22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(WebClientImportSelector.class)
public @interface EnableWebClient {

}
