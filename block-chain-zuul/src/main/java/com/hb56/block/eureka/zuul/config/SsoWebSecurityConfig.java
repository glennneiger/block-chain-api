package com.hb56.block.eureka.zuul.config;

import com.hb56.resource.config.SecuritySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.web.cors.CorsUtils;

/**
 * @author Been
 * @date 2018/12/20
 */
@Configuration
@EnableOAuth2Sso
@EnableConfigurationProperties(SecuritySettings.class)
public class SsoWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    private final SecuritySettings securitySettings;

    @Autowired
    public SsoWebSecurityConfig(SecuritySettings securitySettings, OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails) {
        this.securitySettings = securitySettings;
        this.oAuth2ProtectedResourceDetails = oAuth2ProtectedResourceDetails;
    }
//
//    @Bean
//    public ResourceOwnerPasswordResourceDetails details() {
//        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
//        details.setClientId(oAuth2ProtectedResourceDetails.getClientId());
//        details.setClientSecret(oAuth2ProtectedResourceDetails.getClientSecret());
//        details.setAccessTokenUri(oAuth2ProtectedResourceDetails.getAccessTokenUri());
//        return details;
//    }

    /**
     * token中继
     * OauthClient/OauthSso
     */
    @Bean
//    @LoadBalanced
    public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext context) {
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, context);
    }

    /**
     * token中继
     * ResourceServer
     */
//    @Bean
//    public OAuth2RestTemplate oAuth2RestTemplate(UserInfoRestTemplateFactory factory) {
//        return factory.getUserInfoRestTemplate();
//    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行预请求方法
        web.ignoring().requestMatchers(CorsUtils::isPreFlightRequest);

    }

    @Bean
    public CustomLogoutHandler logoutHandler() {
        return new CustomLogoutHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .logout()
                .addLogoutHandler(logoutHandler())
                .logoutSuccessUrl("/out")
                .and()
                .authorizeRequests()
                .antMatchers(securitySettings.getPermit().split(",")).permitAll()
                .anyRequest().authenticated();
    }

}
