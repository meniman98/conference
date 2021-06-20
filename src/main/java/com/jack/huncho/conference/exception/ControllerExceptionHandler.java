package com.jack.huncho.conference.exception;

import com.jack.huncho.conference.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage validationHandler(ValidationException e) {
        return new ErrorMessage(e.getMessage(), "400");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    ErrorMessage notFoundHandler(EntityNotFoundException e) {
        return new ErrorMessage(Constants.NOT_FOUND, Constants.NOT_FOUND_STATUS_CODE);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    ErrorMessage internalErrorHandler(HttpServerErrorException.InternalServerError e) {
        return new ErrorMessage(e.getMessage(), e.getStatusCode().toString());
    }
}
