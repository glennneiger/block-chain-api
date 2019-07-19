package com.hb56.resource.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.cors.CorsUtils;

/**
 * @author Been
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecuritySettings.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RemoteResourceConfig extends ResourceServerConfigurerAdapter {

    private final SecuritySettings securitySettings;

    public RemoteResourceConfig(SecuritySettings securitySettings) {
        this.securitySettings = securitySettings;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(securitySettings.getResourceId())
                .stateless(true);
    }

    /**
     * 默认STATELESS
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(securitySettings.getPermit().replaceAll("\\s*", "").split(",")).permitAll()
                .anyRequest().authenticated();
    }
}
