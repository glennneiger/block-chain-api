package com.hb56.block.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @author Been
 */
public class FeignOauthInterceptor implements RequestInterceptor {

    private static final String BEARER = "Bearer";

    private static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (!requestTemplate.headers().containsKey(AUTHORIZATION)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
                requestTemplate.header(AUTHORIZATION, String.format("%s %s", BEARER, details.getTokenValue()));
            }
        }

    }
}
