package com.example.vaccinationbookingsystem.exceptions;

public class DoseAlreadyTaken extends RuntimeException{

    public DoseAlreadyTaken(String message){
        super(message);
    }
}
