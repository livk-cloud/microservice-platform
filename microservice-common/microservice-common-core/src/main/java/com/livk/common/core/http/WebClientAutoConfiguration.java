package com.livk.common.core.http;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.DefaultSslContextSpec;

import java.time.Duration;
import java.util.function.Function;

/**
 * <p>
 * WebClientAutoConfiguration
 * </p>
 *
 * @author livk
 * @date 2022/9/22
 */
@AutoConfiguration
@EnableConfigurationProperties(WebClientProperties.class)
public class WebClientAutoConfiguration {

    @Bean
    @ConditionalOnClass(ConnectionProvider.class)
    public ReactorResourceFactory reactorResourceFactory() {
        ReactorResourceFactory factory = new ReactorResourceFactory();
        factory.setUseGlobalResources(false);
        return factory;
    }

    @Bean
    @ConditionalOnClass(WebClient.class)
    @ConditionalOnMissingBean
    public WebClient webClient(ReactorResourceFactory reactorResourceFactory,
                               WebClientProperties properties) {
        Function<HttpClient, HttpClient> function = httpClient ->
                httpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
                        .responseTimeout(Duration.ofSeconds(properties.getResponseTimeout()))
                        .secure(sslContextSpec -> sslContextSpec.sslContext(
                                DefaultSslContextSpec.forClient()
                                        .configure(builder -> builder.trustManager(InsecureTrustManagerFactory.INSTANCE))))
                        .doOnConnected(connection ->
                                connection.addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout()))
                                        .addHandlerLast(new WriteTimeoutHandler(properties.getWriteTimeout())));
        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(reactorResourceFactory, function);
        return WebClient.builder().clientConnector(connector).build();
    }
}
