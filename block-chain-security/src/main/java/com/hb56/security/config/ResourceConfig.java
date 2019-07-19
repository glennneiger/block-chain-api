package com.hb56.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Been
 * @date 2018/12/27
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceConfig extends ResourceServerConfigurerAdapter {

//    @Bean
//    public CustomAuthenticationEntryPoint entryPoint() {
//        return new CustomAuthenticationEntryPoint();
//    }


    @Bean
    public CustomLogoutSuccessHandler logoutHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessHandler(logoutHandler())
                .and().authorizeRequests()
                .anyRequest().authenticated();
    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.authenticationEntryPoint(entryPoint());
//    }
}
