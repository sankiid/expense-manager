package com.expense.manager.exception;

import com.expense.manager.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseEntity<Response> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        logger.warn("something went wrong ", ex.getMessage());
        Response response = new Response(HttpStatus.NOT_FOUND.value(), null, ex.getMessage(),
                                                                        "failed to serve the request");
        return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Response> handleEmptyResultDataAccessException(ValidationException ex) {
        logger.warn("validation failed {}", ex.getMessage());
        Response response = new Response(HttpStatus.BAD_REQUEST.value(), null, ex.getMessage(),
                "validation failed");
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    protected ResponseEntity<Response> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        logger.error("category not found  ", ex.getMessage());
        Response response = new Response(HttpStatus.NOT_FOUND.value(), null, ex.getMessage(),
                "Category not listed");
        return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
    }
}
