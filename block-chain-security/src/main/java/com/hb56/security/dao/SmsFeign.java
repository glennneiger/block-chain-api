package com.hb56.security.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * @author Been
 */
@FeignClient(url = "http://172.16.19.171:8000", name = "sms")
public interface SmsFeign {
    @PostMapping("/proxies/messages/sms")
    void getSmsCode(Map map, @RequestHeader("host") String host, @RequestHeader("apiKey") String apiKey);
}
