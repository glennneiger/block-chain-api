package com.hb56.security.config;

import com.hb56.resource.config.TokenStoreConfig;
import com.hb56.security.service.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Been
 * @date 2018/12/27
 */
@Configuration
@EnableAuthorizationServer
@Import(TokenStoreConfig.class)
public class AuthorizeServerConfig extends AuthorizationServerConfigurerAdapter {

    private final RedisTokenStore redisTokenStore;
    private final OceanRailAuthenticationProvide oceanRailAuthenticationProvide;
    private final CustomUserServiceImpl customUserService;
    private final AuthenticationManager authenticationManager;
    private final DataSource dataSource;

    @Autowired
    public AuthorizeServerConfig(CustomUserServiceImpl customUserService, AuthenticationManager authenticationManager, DataSource dataSource, OceanRailAuthenticationProvide oceanRailAuthenticationProvide, RedisTokenStore redisTokenStore) {
        this.customUserService = customUserService;
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
        this.oceanRailAuthenticationProvide = oceanRailAuthenticationProvide;
        this.redisTokenStore = redisTokenStore;
    }

    /**
     * 客户端一些配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetails());
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 自定义登录认证(sms_code)
     *
     * @return
     */
    @Bean
    public SmsCodeAuthenticationProvider smsCodeAuthenticationProvider() {
        return new SmsCodeAuthenticationProvider();
    }


    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        granters.add(new SmsTokenGranter(smsCodeAuthenticationProvider(), endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        granters.add(new OceanRailTokenGranter(oceanRailAuthenticationProvide, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }


    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    /**
     * springSecurity 授权表达式，访问tokenKey时经过认证
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
//                .allowFormAuthenticationForClients();
    }

    @Bean
    public CustomUserAuthConverter customUserAuthConverter() {
        return new CustomUserAuthConverter();
    }

    /**
     * 配置TokenStore
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        将增强的token设置到增强链中
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(tokenEnhancer()));
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(customUserAuthConverter());
//        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
        endpoints
                .tokenStore(redisTokenStore)
                .accessTokenConverter(defaultAccessTokenConverter)
//                .tokenStore(jwtTokenStore())
                .tokenEnhancer(tokenEnhancerChain)
                .userDetailsService(customUserService)
                .authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager)
                .tokenGranter(tokenGranter(endpoints));
//                .exceptionTranslator(webResponseExceptionTranslator());
    }

//    /**
//     * JWTtokenStore
//     */
//    @Bean
//    public TokenStore jwtTokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Bean
//    public KeyProperties keyProperties() {
//        return new KeyProperties();
//    }
//
//    /**
//     * 生成JTW token
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory
//                (keyProperties().getKeyStore().getLocation(), keyProperties().getKeyStore().getSecret().toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyProperties().getKeyStore().getAlias()));
//        return converter;
//    }

//   @Bean
//    public JdbcTokenStore jdbcTokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }

//    @Bean
//    public OAuth2RequestFactory requestFactory() {
//        return new DefaultOAuth2RequestFactory(clientDetails());
//    }

//    @Bean
//    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
//        return new CustomWebResponseExceptionTranslator();
//    }

//    @Bean
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        //将增强的token设置到增强链中
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(tokenEnhancer()));
//        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
//        tokenServices.setAccessTokenValiditySeconds(600);
//        tokenServices.setRefreshTokenValiditySeconds(800);
//        tokenServices.setClientDetailsService(clientDetails());
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setTokenEnhancer(tokenEnhancerChain);
//        tokenServices.setTokenStore(redisTokenStore());
//        tokenServices.setTokenStore(jwtTokenStore());
//        tokenServices.setTokenStore(jdbcTokenStore());
//        return tokenServices;
//    }

}
