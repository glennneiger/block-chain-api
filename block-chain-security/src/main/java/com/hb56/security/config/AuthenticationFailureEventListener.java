package com.hb56.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Been
 * @date 2019/5/9
 */
@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private final StringRedisTemplate stringRedisTemplate;
    private final LockUserSettings settings;

    @Autowired
    public AuthenticationFailureEventListener(LockUserSettings settings, StringRedisTemplate stringRedisTemplate) {
        this.settings = settings;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        Authentication authentication = authenticationFailureBadCredentialsEvent.getAuthentication();
        if (authentication.getDetails() == null) {
            return;
        }
        if (authentication.getDetails() instanceof WebAuthenticationDetails) {
            return;
        }
        String username = authentication.getName();
        String value = stringRedisTemplate.opsForValue().get(username + settings.getKeyLock());
        String msg;
        if (String.valueOf(settings.getFailCount()).equals(value)) {
            msg = String.format("密码连续错误次数超限已锁定,请%s小时后重试", settings.getLockedHours());
            stringRedisTemplate.opsForValue().set(username + settings.getKeyLocked(), username, settings.getLockedHours(), TimeUnit.HOURS);
            throw new InvalidGrantException(msg);
        } else {
            int count = (value == null ? 1 : Integer.parseInt(value));
            msg = String.format("密码错误,已连续失败%s次,%s次后将被锁定%s小时", count, settings.getFailCount(), settings.getLockedHours());
            stringRedisTemplate.opsForValue().set(username + settings.getKeyLock(), String.valueOf(++count), settings.getLockHours(), TimeUnit.HOURS);
            throw new InvalidGrantException(msg);
        }
    }
}
