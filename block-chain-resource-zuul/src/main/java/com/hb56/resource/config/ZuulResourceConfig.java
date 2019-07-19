package com.hb56.resource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.web.cors.CorsUtils;

/**
 * @author Been
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(SecuritySettings.class)
@Import(TokenStoreConfig.class)
public class ZuulResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    private final SecuritySettings securitySettings;

    public ZuulResourceConfig(SecuritySettings securitySettings) {
        this.securitySettings = securitySettings;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .expressionHandler(expressionHandler)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(securitySettings.getPermit().replaceAll("\\s*", "").split(",")).permitAll()
                .antMatchers(securitySettings.getAuthenticate().replaceAll("\\s*", "").split(",")).authenticated()
                .anyRequest().access("authenticated and " + securitySettings.getAccessExpression());
    }

}
