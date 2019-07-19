package com.hb56.security.config;

import com.hb56.security.service.CustomUserServiceImpl;
import com.hb56.security.service.OceanRailUserServiceImpl;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;


/**
 * @author Been
 * @date 2018/12/20
 */
@Configuration
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * grant_typ e=password
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CustomUserServiceImpl customUserService() {
        return new CustomUserServiceImpl();
    }

    @Bean
    OceanRailUserServiceImpl otherCustomService() {
        return new OceanRailUserServiceImpl();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserService());
        return daoAuthenticationProvider;
    }

    @Bean
    public OceanRailAuthenticationProvide oceanRailAuthenticationProvide() {
        OceanRailAuthenticationProvide oceanRailAuthenticationProvide = new OceanRailAuthenticationProvide();
        oceanRailAuthenticationProvide.setPasswordEncoder(passwordEncoder());
        oceanRailAuthenticationProvide.setUserDetailsService(otherCustomService());
        return oceanRailAuthenticationProvide;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
//        auth.userDetailsService(custom()).passwordEncoder(passwordEncoder());

    }

    @Bean
    public CustomLoginSuccessHandler loginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        //放行预请求方法
        web.ignoring().requestMatchers(CorsUtils::isPreFlightRequest);
        web.ignoring().antMatchers("/css/**", "/favicon.ico");
//        web.ignoring().antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs/**", "/webjars/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
          requestMatchers().antMatchers()方法如下：
          属于顶级策略,与顺序无关
          指定接受请求：默认permitAll(),即直接放行的请求url需要添加到这里
          然后由于@order顺序,resourceConfig里面相同url策略失效
         */

        http
                .cors()
                .and()
                .csrf().disable()
                .requestMatchers().antMatchers("/login", "/oauth/authorize","/sms_code")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().loginPage("/login").successHandler(loginSuccessHandler());

    }

}
