package com.hb56.security.fallback;

import com.alibaba.fastjson.JSON;
import com.hb56.common.result.RestInfo;
import com.hb56.security.dao.SecurityUserFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

/**
 * @author Been
 */
@Service
public class SecurityUserFallback implements FallbackFactory<SecurityUserFeign> {
    @Override
    public SecurityUserFeign create(Throwable throwable) {
        return new SecurityUserFeign() {
            @Override
            public RestInfo getUserAccount(String account) {
                RestClientResponseException ex = (RestClientResponseException) throwable;
                return JSON.parseObject(ex.getStatusText(), RestInfo.class);
            }

            @Override
            public RestInfo getPhoneUser(String phone) {
                RestClientResponseException ex = (RestClientResponseException) throwable;
                return JSON.parseObject(ex.getStatusText(), RestInfo.class);
            }
        };
    }
}
