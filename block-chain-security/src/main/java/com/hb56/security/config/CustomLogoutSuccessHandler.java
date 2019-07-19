package com.hb56.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Been
 * @date 2019/4/9
 */
public class CustomLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer";
    private static final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
    @Autowired
    private RedisTokenStore tokenStore;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("session:" + httpServletRequest.getRequestedSessionId());
        String authHeader = httpServletRequest.getHeader(AUTHORIZATION);
        if (authHeader != null) {
            String tokenValue = authHeader.replace(BEARER, "").trim();
            logger.info("access_token:" + tokenValue);
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(tokenValue);
            if (oAuth2AccessToken != null) {
                consumerTokenServices.revokeToken(oAuth2AccessToken.getValue());
            }
        }
    }
}


