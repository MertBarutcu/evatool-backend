package com.evatool.requirements.error;


import com.evatool.requirements.error.exceptions.EntityNotFoundException;
import com.evatool.requirements.error.exceptions.IllegalDtoValueExcpetion;
import com.evatool.requirements.repository.RequirementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RequirementExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequirementExceptionHandler.class);


    @Autowired
    private RequirementRepository requirementRepository;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RequirementsErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest webRequest) {
        logger.info("{} handled. Returning HttpStatus NOT_FOUND (404)", exception.getClass().getSimpleName());
        RequirementsErrorMessage errorMessage = new RequirementsErrorMessage(exception, exception.getMessage(), getUri(webRequest), HttpStatus.NOT_FOUND);
        if(exception.isRollback()){
            requirementRepository.delete(exception.getRequirement());
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalDtoValueExcpetion.class)
    public ResponseEntity<RequirementsErrorMessage> handleIllegalDtoValueExcpetion(IllegalDtoValueExcpetion exception, WebRequest webRequest) {
        logger.info("{} handled. Returning HttpStatus UNPROCESSABLE_ENTITY (422)", exception.getClass().getSimpleName());
        RequirementsErrorMessage errorMessage = new RequirementsErrorMessage(exception, exception.getMessage(), getUri(webRequest), HttpStatus.UNPROCESSABLE_ENTITY);
        requirementRepository.delete(exception.getRequirement());
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private String getUri(WebRequest webRequest) {
        return ((ServletWebRequest) webRequest).getRequest().getRequestURI();
    }
}
