package com.fexco.postcoder.webrestcache.infra.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Responsible for Handling Exceptions that Service and Consumer throw to Controller's classes.
 * It is important to avoid boilerplate exception handling in every call.
 * @author Emival Junior
 * @version 1.0
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = { HttpClientErrorException.class, HttpServerErrorException.class })
    protected ResponseEntity<Object> handleExternalCallError(RuntimeException ex, WebRequest request) {
        HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) ex;
        return handleExceptionInternal(ex, httpStatusCodeException.getResponseBodyAsString(),
                new HttpHeaders(), httpStatusCodeException.getStatusCode(), request);
    }

}
