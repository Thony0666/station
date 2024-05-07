package com.anthonyo.station.rasta.advices;

import com.anthonyo.station.rasta.exceptions.InternalServerException;
import com.anthonyo.station.rasta.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalRestControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody AppError appError(NotFoundException exception){
        return new AppError(exception.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
    }
    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody AppError appError(InternalServerException exception){
        return new AppError(exception.getMessage(), LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
