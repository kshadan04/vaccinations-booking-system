package com.example.vaccinationbookingsystem.exceptions;

public class CenterNotFoundException extends RuntimeException{
    public CenterNotFoundException(String message){
        super(message);
    }

}
