package com.livk.common.core.http;

import com.livk.common.core.http.annotation.EnableWebClient;
import com.livk.common.core.support.AbstractImportSelector;

/**
 * <p>
 * WebClientImportSelector
 * </p>
 *
 * @author livk
 * @date 2022/9/22
 */
public class WebClientImportSelector extends AbstractImportSelector<EnableWebClient> {

    private static final String ENABLE_KEY = "spring.webflux.webclient.enable";

    @Override
    protected boolean isEnabled() {
        return environment.getProperty(ENABLE_KEY, Boolean.class, Boolean.TRUE);
    }
}
