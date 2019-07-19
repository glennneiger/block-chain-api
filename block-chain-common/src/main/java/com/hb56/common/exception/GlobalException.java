package com.hb56.common.exception;

import com.hb56.common.result.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;


/**
 * @author Been
 * @date 2018/10/11
 */
@RestControllerAdvice
public class GlobalException {
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 403抛给auth
     *
     * @param e
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(AccessDeniedException e) {
        throw e;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(RestUtil.setErrorMsg("业务异常", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        logger.error(e.getMessage(), e);
        /*
          判断是否由feign熔断异常引起
         */
        if (e.getCause() instanceof RestClientResponseException) {
            RestClientResponseException ex = (RestClientResponseException) e.getCause();
            return new ResponseEntity<>(ex.getStatusText(), HttpStatus.valueOf(ex.getRawStatusCode()));
        }
        return new ResponseEntity<>(RestUtil.setErrorMsg("系统异常", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
