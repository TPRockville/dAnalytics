package org.jderive.controller;

import lombok.extern.slf4j.Slf4j;
import org.jderive.api.JDeriveResponse;
import org.jderive.exception.JDeriveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Durga on 6/23/2015.
 */
@ControllerAdvice
@Slf4j
public class JDeriveAdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JDeriveException.class)
    private ResponseEntity<JDeriveResponse> handleJDeriveException(Exception re, WebRequest webRequest) {
        log.error("Error: ", re);
        return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                .withStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .withMessage(re.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
