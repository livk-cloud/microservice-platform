package com.livk.common.core.http;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * WebClientProperties
 * </p>
 *
 * @author livk
 * @date 2022/9/22
 */
@Data
@ConfigurationProperties(prefix = WebClientProperties.PREFIX)
public class WebClientProperties {

    public static final String PREFIX = "spring.webflux.webclient";
    /**
     * 连接超时时间
     */
    private Integer connectTimeout = 3_000;
    /**
     * 响应超时时间
     */
    private Long responseTimeout = 15L;
    /**
     * 读取超时时间
     */
    private Integer readTimeout = 20;
    /**
     * 写入超时时间
     */
    private Integer writeTimeout = 20;
}
