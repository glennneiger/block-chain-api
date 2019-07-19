package com.hb56.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author Been
 */
public class TokenStoreConfig {

    @Bean
    public RedisTokenStore redisTokenStore(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTokenStore redisTokenStore = new RedisTokenStore(lettuceConnectionFactory);
        redisTokenStore.setPrefix("Been:");
        return redisTokenStore;
    }
    //    /**
//     * JWTtokenStore
//     *
//     * @return
//     */
//    @Bean
//    public TokenStore jwtTokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    /**
//     * 生成JTW token
//     *
//     * @return
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setVerifierKey(getPubKey());
//        return converter;
//    }
//
//    /**
//     * 获取非对称加密公钥 Key
//     *
//     * @return 公钥 Key
//     */
//    private String getPubKey() {
//        Resource resource = new ClassPathResource("public.txt");
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
//            return br.lines().collect(Collectors.joining("\n"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//
//    /**
//     * 通过访问授权服务器获取非对称加密公钥 Key
//     * @return 公钥 Key
//     */
//    private String getKeyFromAuthorizationServer() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String pubKey = new RestTemplate().getForObject(resourceServerProperties.getJwt().getKeyUri(), String.class);
//        try {
//            Map map = objectMapper.readValue(pubKey, Map.class);
//            return map.get("value").toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
