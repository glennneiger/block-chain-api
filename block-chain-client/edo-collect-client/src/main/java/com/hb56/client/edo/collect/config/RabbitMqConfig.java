package com.hb56.client.edo.collect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;

/**
 * @author Been
 */
@Configuration
@Order(15)
@ImportResource({"classpath:*/*.xml"})
public class RabbitMqConfig {
}
