package com.ecommerce.advice;

import com.ecommerce.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginException(HttpServletRequest request, LoginException loginException) {

        String errorMessage = "Username or Password is invalid";

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(HttpServletRequest request, Exception exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getStackTrace());
    }
}
