package com.hb56.block.config.feign;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;


/**
 * 自定义错误
 *
 * @author Been
 */
public class CustomErrorDecoder implements ErrorDecoder {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Exception decode(String methodKey, Response response) {
//        if (response.status() >= 400 && response.status() <= 499) {
        String json = "";
        try {
            json = Util.toString(response.body().asReader());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
//      不进入熔断逻辑 HystrixBadRequestException
//        }
        return new RestClientResponseException(methodKey, response.status(), json, null, null, null);
    }
}