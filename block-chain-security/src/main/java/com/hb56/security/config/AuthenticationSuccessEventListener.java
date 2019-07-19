package com.hb56.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @author Been
 * @date 2019/5/10
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final StringRedisTemplate stringRedisTemplate;
    private final LockUserSettings settings;

    @Autowired
    public AuthenticationSuccessEventListener(StringRedisTemplate stringRedisTemplate, LockUserSettings settings) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.settings = settings;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        Authentication authentication = authenticationSuccessEvent.getAuthentication();
        if (authentication.getDetails() == null) {
            return;
        }
        if (authentication.getDetails() instanceof WebAuthenticationDetails) {
            return;
        }
        stringRedisTemplate.delete(authentication.getName() + settings.getKeyLock());
    }
}
