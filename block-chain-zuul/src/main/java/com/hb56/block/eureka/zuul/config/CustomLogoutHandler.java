package com.hb56.block.eureka.zuul.config;

import com.hb56.resource.config.SecuritySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Been
 * @date 2019/4/10
 */
public class CustomLogoutHandler implements LogoutHandler {
    @Autowired
    private SecuritySettings securitySettings;

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("auth".equals(cookie.getName())) {
                    headers.add("Cookie", "auth=" + cookie.getValue());
                }
            }
        }

        HttpEntity request = new HttpEntity(headers);
        restTemplate.exchange(securitySettings.getRevokeTokenUri(), HttpMethod.DELETE, request, String.class);
    }

}
