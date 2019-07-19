package com.hb56.block.config.feign;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;


/**
 * @author Been
 */
@Configuration
@Import(FeignConfig.class)
public class ResourceFeignConfig {

    /**
     * 处理HystrixSecurityAutoConfiguration里concurrency注入问题
     * tracing...
     * security...
     * sleuth...
     */
    @Primary
    @Bean
    @ConditionalOnProperty(name = "hystrix.shareSecurityContext")
    public HystrixConcurrencyStrategy hystrixConcurrencyStrategy() {
        return null;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignOauthInterceptor();
    }

//    @Bean
//    public ResourceOwnerPasswordResourceDetails details() {
//        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
//        details.setAccessTokenUri("http://172.16.40.198:32189/oauth/token");
//        details.setClientSecret("secret");
//        details.setClientId("esb");
//        return details;
//    }
//
//    @Bean
//    @Primary
//    public OAuth2ClientContext oAuth2ClientContext() {
//        return new DefaultOAuth2ClientContext() {
//            @Override
//            public OAuth2AccessToken getAccessToken() {
//                return Optional.ofNullable(super.getAccessToken())
//                        .orElse(new DefaultOAuth2AccessToken(
//                                ((OAuth2AuthenticationDetails) SecurityContextHolder
//                                        .getContext().getAuthentication().getDetails())
//                                        .getTokenValue()));
//            }
//        };
//    }
//
//    @Bean
//    public OAuth2RestTemplate oAuth2RestTemplate() {
//        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, oAuth2ClientContext());
//    }
//
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext(), oAuth2ProtectedResourceDetails);
//    }

}
