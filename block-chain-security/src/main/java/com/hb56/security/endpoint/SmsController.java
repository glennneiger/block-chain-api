package com.hb56.security.endpoint;

import com.hb56.common.result.RestInfo;
import com.hb56.common.result.RestUtil;
import com.hb56.security.config.SmsServicesSettings;
import com.hb56.security.dao.SmsFeign;
import com.hb56.security.util.RandomCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Been
 */
@RestController
public class SmsController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SmsServicesSettings smsServicesSettings;
    @Autowired
    private SmsFeign feign;

    @GetMapping("/sms_code")
    public RestInfo getMessage(@RequestParam String number) {
        String code = RandomCode.createSmsCode(smsServicesSettings.getLength());
        redisTemplate.opsForValue().set(number, code, smsServicesSettings.getSmsValidTimeInterval(), TimeUnit.SECONDS);
        Map<String, Object> params = new HashMap<>(8);
        params.put("from", "9920000001");
        params.put("version", 1);
        params.put("to", number);
        params.put("message", String.format("您的验证码%s验证码有效时间%s分钟请及时使用", code, smsServicesSettings.getSmsValidTimeInterval() / 60));
        feign.getSmsCode(params, "api.e-truck.com.cn", "OBYG65LFMVVXAMDJNBYG24LOOZXXC3TX");
        return RestUtil.setSuccessMsg(code);
    }

}
