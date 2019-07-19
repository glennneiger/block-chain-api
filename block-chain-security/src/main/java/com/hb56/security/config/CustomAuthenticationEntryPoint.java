//package com.hb56.security.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.provider.error.*;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author Been
// * @date 2019/4/29
// */
//public class CustomAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {
//    @Autowired
//    private WebResponseExceptionTranslator exceptionTranslator;
//    private OAuth2ExceptionRenderer exceptionRenderer = new DefaultOAuth2ExceptionRenderer();
//    private HandlerExceptionResolver handlerExceptionResolver = new DefaultHandlerExceptionResolver();
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        try {
//            ResponseEntity<?> result = exceptionTranslator.translate(authException);
//            result = enhanceResponse(result, authException);
//            exceptionRenderer.handleHttpEntityResponse(result, new ServletWebRequest(request, response));
//            response.flushBuffer();
//        } catch (ServletException e) {
//            // Re-use some of the default Spring dispatcher behaviour - the exception came from the filter chain and
//            // not from an MVC handler so it won't be caught by the dispatcher (even if there is one)
//            if (handlerExceptionResolver.resolveException(request, response, this, e) == null) {
//                throw e;
//            }
//        } catch (IOException | RuntimeException e) {
//            throw e;
//        } catch (Exception e) {
//            // Wrap other Exceptions. These are not expected to happen
//            throw new RuntimeException(e);
//        }
//    }
//}
