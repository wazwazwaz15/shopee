package org.weiga.shopee.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.Map;

@ControllerAdvice
public class AllExcpetionHandler {


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handlerNotAuthorized(AuthenticationException ex) {


        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "登入失敗", "message", ex.getMessage()));
    }


}
