//package com.hb56.block.config.feign;
//
//import feign.RequestTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
//import org.springframework.security.oauth2.client.token.AccessTokenProvider;
//import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
//import org.springframework.security.oauth2.client.token.AccessTokenRequest;
//import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
//import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2RefreshToken;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//import java.util.Collections;
//
///**
// * @author Been
// */
//public class CustomOauthInterceptor extends OAuth2FeignRequestInterceptor {
//
//
//    @Autowired
//    private ResourceOwnerPasswordResourceDetails resourceDetails;
//
//    @Autowired
//    private RedisTokenStore redisTokenStore;
//
//    public CustomOauthInterceptor(OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails resource) {
//        super(oAuth2ClientContext, resource);
//    }
//
//    private AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(Collections.<AccessTokenProvider>singletonList(new ResourceOwnerPasswordAccessTokenProvider())
//    );
//
//    @Override
//    public void apply(RequestTemplate template) {
//        super.apply(template);
//    }
//
//    @Override
//    protected String extract(String tokenType) {
//        OAuth2AccessToken accessToken = getToken();
//        return String.format("%s %s", tokenType, accessToken.getValue());
//    }
//
//    @Override
//    public OAuth2AccessToken getToken() {
//        OAuth2AccessToken accessToken = redisTokenStore.readAccessToken(getTokenValue());
//        if (accessToken != null && accessToken.isExpired()) {
//            accessToken = acquireAccessTokenByRefresh(accessToken);
//        }
//        return accessToken;
//    }
//
//    protected OAuth2AccessToken acquireAccessTokenByRefresh(OAuth2AccessToken accessToken)
//            throws UserRedirectRequiredException {
//        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
//        AccessTokenRequest tokenRequest = new DefaultAccessTokenRequest();
//
//        OAuth2AccessToken obtainableAccessToken;
//        obtainableAccessToken = accessTokenProvider.refreshAccessToken(resourceDetails, refreshToken, tokenRequest);
//        if (obtainableAccessToken == null || obtainableAccessToken.getValue() == null) {
//            throw new IllegalStateException(
//                    " Access token provider returned a null token, which is illegal according to the contract.");
//        }
//        return obtainableAccessToken;
//    }
//
//
//    private String getTokenValue() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
//            return ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
//        }
//        return null;
//    }
//}
