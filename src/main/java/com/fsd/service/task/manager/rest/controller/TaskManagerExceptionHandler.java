package com.fsd.service.task.manager.rest.controller;

import com.fsd.service.task.manager.exception.ResourceNotFoundException;
import com.fsd.service.task.manager.exception.TaskManagerInvalidRequestException;
import com.fsd.service.task.manager.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class TaskManagerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskManagerInvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidRequestException(TaskManagerInvalidRequestException e) {
        logger.error("TaskManagerInvalidRequestException : {}", e);
        return buildErrorMessage(e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(Exception e) {
        logger.error("ResourceNotFoundException", e);
        return buildErrorMessage(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception e) {
        logger.error("Exception", e);
        return buildErrorMessage(e);
    }

    private ErrorMessage buildErrorMessage(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode("Unknown");
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

}
