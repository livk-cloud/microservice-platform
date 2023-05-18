package com.livk.common.gateway.filter;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.LimitedDataBufferList;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * ResultHandlerWebFilter
 * </p>
 *
 * @author livk
 */
public abstract class ResultHandlerWebFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        if (support(originalResponse)) {
            ServerHttpResponse decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                @NonNull
                @Override
                public Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
                    Mono<DataBuffer> dataBufferFlux = Flux.from(body)
                            .collect(() -> new LimitedDataBufferList(-1), LimitedDataBufferList::add)
                            .filter((list) -> !list.isEmpty()).map((list) -> list.get(0).factory().join(list))
                            .doOnDiscard(DataBuffer.class, DataBufferUtils::release)
                            .map(dataBuffer -> {
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);
                                String result = new String(content, StandardCharsets.UTF_8);

                                byte[] bytes = resultHandler(result);
                                getHeaders().setContentLength(bytes.length);
                                getHeaders().setContentType(MediaType.APPLICATION_JSON);
                                return bufferFactory().wrap(bytes);
                            });
                    return super.writeWith(dataBufferFlux);
                }
            };
            return chain.filter(exchange.mutate().response(decoratedResponse).build());
        }
        return chain.filter(exchange);
    }

    protected abstract boolean support(ServerHttpResponse response);

    protected abstract byte[] resultHandler(String result);

}
