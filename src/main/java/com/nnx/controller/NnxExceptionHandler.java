package com.nnx.controller;

import static com.nnx.common.CommonConstants.NNX_STATUS_CODE_HEADER;
import static com.nnx.common.CommonConstants.NNX_STATUS_MESSAGE_HEADER;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nnx.common.LoggerUtil;

/**
 * The base exception handler for all the controller exception handling.
 * 
 * @author hafeez
 */
@ControllerAdvice
public class NnxExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(NnxExceptionHandler.class);

    private final static String INVALID_ARGUMENTS_ERROR = "INVALID_ARGUMENTS_ERROR";

    private final static String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody ResponseEntity<?> handleIllegalArgumentException(
            final IllegalArgumentException illegalArgumentException) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(NNX_STATUS_CODE_HEADER, INVALID_ARGUMENTS_ERROR);
        httpHeaders.add(NNX_STATUS_MESSAGE_HEADER, illegalArgumentException.getMessage());

        final ResponseEntity<?> responseEntity = new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);

        LoggerUtil.logError(logger, "Illegal argument error: {}", illegalArgumentException);

        return responseEntity;
    }

    @ExceptionHandler(IllegalStateException.class)
    public @ResponseBody ResponseEntity<?> handleIllegalStateException(
            final IllegalStateException illegalStateException) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(NNX_STATUS_CODE_HEADER, INVALID_ARGUMENTS_ERROR);
        httpHeaders.add(NNX_STATUS_MESSAGE_HEADER, illegalStateException.getMessage());

        final ResponseEntity<?> responseEntity = new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);

        LoggerUtil.logError(logger, "Illegal state error: {}", illegalStateException);

        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity<?> handleInternalServerError(final Exception exception) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(NNX_STATUS_CODE_HEADER, INTERNAL_SERVER_ERROR);
        httpHeaders.add(NNX_STATUS_MESSAGE_HEADER, exception.getMessage());

        final ResponseEntity<?> responseEntity = new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

        LoggerUtil.logError(logger, "Internal server error: {}", exception);

        return responseEntity;
    }
}
