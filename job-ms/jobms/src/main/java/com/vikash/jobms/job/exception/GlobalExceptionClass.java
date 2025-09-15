package com.vikash.jobms.job.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionClass {

    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public String companyNotFoundException(Exception e){
        return e.getMessage();
    }

}
