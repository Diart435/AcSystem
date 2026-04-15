package com.example.AcSystemProducer.Exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(String message){
        super(message);
    }
}
