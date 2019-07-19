//package com.hb56.security.config;
//
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//
///**
// * @author Been
// * @date 2019/4/29
// */
//@SuppressWarnings("serial")
//@JsonSerialize(using = CustomOauth2JacksonSerializer.class)
//public class CustomOauth2Exception extends OAuth2Exception {
//
//    private int httpErrorCode;
//    private String oauth2ErrorCode;
//
//    public CustomOauth2Exception(String msg, int httpErrorCode, String oauth2ErrorCode) {
//        super(msg);
//        this.httpErrorCode = httpErrorCode;
//        this.oauth2ErrorCode = oauth2ErrorCode;
//    }
//
//    public CustomOauth2Exception(String msg, Throwable t) {
//        super(msg, t);
//    }
//
//
//    @Override
//    public int getHttpErrorCode() {
//        return httpErrorCode;
//    }
//
//    @Override
//    public String getOAuth2ErrorCode() {
//        return oauth2ErrorCode;
//    }
//}
