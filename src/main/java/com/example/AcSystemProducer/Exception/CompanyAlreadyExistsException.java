package com.example.AcSystemProducer.Exception;

public class CompanyAlreadyExistsException extends RuntimeException{
    public CompanyAlreadyExistsException(String message){
        super(message);
    }
}
