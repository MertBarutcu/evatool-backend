package com.evatool.analysis.error;

import com.evatool.analysis.error.exceptions.IllegalDtoValueExcpetion;
import com.evatool.analysis.error.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AnalysisExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AnalysisErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest webRequest) {
        logger.info("{} handled. Returning HttpStatus NOT_FOUND (404)", exception.getClass().getSimpleName());
        AnalysisErrorMessage errorMessage = new AnalysisErrorMessage(exception, exception.getMessage(), getUri(webRequest), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IllegalDtoValueExcpetion.class)
    public ResponseEntity<AnalysisErrorMessage> handleIllegalDtoValueExcpetion(IllegalDtoValueExcpetion exception, WebRequest webRequest) {
        logger.info("{} handled. Returning HttpStatus UNPROCESSABLE_ENTITY (422)", exception.getClass().getSimpleName());
        AnalysisErrorMessage errorMessage = new AnalysisErrorMessage(exception, exception.getMessage(), getUri(webRequest), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private String getUri(WebRequest webRequest) {
        return ((ServletWebRequest) webRequest).getRequest().getRequestURI();
    }
}
