package com.uit.order_service.helper;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import lombok.val;
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage idException(Exception e,WebRequest request){
           
            return new ErrorMessage(400, "Invalid Id");

    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage noSuchElementException(Exception e,WebRequest request){
        return new ErrorMessage(404, "Product Not Found");

    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage unexpectedException(Exception e,WebRequest request){
        return new ErrorMessage(500,e.getLocalizedMessage());

    }
    // @ExceptionHandler(BadCredentialsException.class)
    // @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    // public ErrorMessage badCredentialException(Exception e,WebRequest request){
    //     return new ErrorMessage(401, "Username or Password is invalid");

    // }
}
