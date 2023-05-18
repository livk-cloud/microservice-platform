package com.livk.common.gateway.support;

import com.livk.common.gateway.exception.RouteCheckException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * <p>
 * RouteCheckFailureAnalyzer
 * </p>
 *
 * @author livk
 */
public class RouteCheckFailureAnalyzer extends AbstractFailureAnalyzer<RouteCheckException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, RouteCheckException cause) {
        return new FailureAnalysis(rootFailure.getMessage(), "", cause);
    }

}
