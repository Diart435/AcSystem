package com.example.AcSystemProducer.Handler;

import com.example.AcSystemProducer.Exception.CompanyAlreadyExistsException;
import com.example.AcSystemProducer.Exception.CompanyNotFoundException;
import com.example.AcSystemProducer.Exception.UserAlreadyExistsException;
import com.example.AcSystemProducer.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, CompanyNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> ErrorNotFound(RuntimeException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({UserAlreadyExistsException.class, CompanyAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> ErrorAlreadyExists(RuntimeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
