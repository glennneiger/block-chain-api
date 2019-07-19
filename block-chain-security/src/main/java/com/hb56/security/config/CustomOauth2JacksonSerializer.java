//package com.hb56.security.config;
//
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import com.hb56.common.result.RestUtil;
//
//import java.io.IOException;
//
///**
// * @author Been
// * @date 2019/4/29
// */
//public class CustomOauth2JacksonSerializer extends StdSerializer<CustomOauth2Exception> {
//
//    protected CustomOauth2JacksonSerializer() {
//        super(CustomOauth2Exception.class);
//    }
//
//    @Override
//    public void serialize(CustomOauth2Exception value, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
//        jgen.writeObject(RestUtil.setErrorCode(value.getHttpErrorCode(), value.getMessage()));
//    }
//}
