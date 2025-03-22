package com.mahumane.restaurants.infrastructure;

import com.mahumane.restaurants.exception.BadRequestException;
import com.mahumane.restaurants.exception.ConflictException;
import com.mahumane.restaurants.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mahumane.restaurants.dto.response.ExceptionsResponse;
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public Object conflictException(ConflictException ex){
        return  ExceptionsResponse.exceptionResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public Object notFoundException(NotFoundException ex){
        return ExceptionsResponse.exceptionResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public Object badRequestException(BadRequestException ex){
        return ExceptionsResponse.exceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());

    }


}
