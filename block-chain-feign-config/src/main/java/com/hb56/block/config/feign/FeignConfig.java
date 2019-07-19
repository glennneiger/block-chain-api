package com.hb56.block.config.feign;

import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @author Been
 */
@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringMultipartEncoder(new SpringEncoder(messageConverters));
    }
//
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return template -> {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//                    .getRequestAttributes();
//            HttpServletRequest request = attributes != null ? attributes.getRequest() : null;
//            Enumeration<String> headerNames = request != null ? request.getHeaderNames() : null;
//            if (headerNames != null) {
//                while (headerNames.hasMoreElements()) {
//                    String name = headerNames.nextElement();
//                    String values = request.getHeader(name);
//                    template.header(name, values);
//                }
//            }
//        };
//    }

//    @Bean
//    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
//        return new FeignHystrixConcurrencyStrategy();
//    }

}
