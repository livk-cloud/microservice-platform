package com.livk.common.gateway.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * <p>
 * MicroRoute
 * </p>
 *
 * @author livk
 * @date 2021/11/2
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonPropertyOrder(alphabetic = true)
public class MicroRoute extends RouteDefinition {

    /**
     * 描述
     */
    private String description;

    /**
     * 状态：1-有效，0-无效
     */
    private Integer status;

}
