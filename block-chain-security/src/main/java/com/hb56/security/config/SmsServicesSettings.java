package com.hb56.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author Been
 */
@Component
@ConfigurationProperties(prefix = "sms-services-config")
public class SmsServicesSettings {
    private int length;

    private long smsValidTimeInterval;

    public long getSmsValidTimeInterval() {
        return smsValidTimeInterval;
    }

    public void setSmsValidTimeInterval(long smsValidTimeInterval) {
        this.smsValidTimeInterval = smsValidTimeInterval;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
