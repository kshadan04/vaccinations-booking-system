package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Enum.DoseType;
import com.example.vaccinationbookingsystem.dto.requestDto.BookDose1RequestDto;
import com.example.vaccinationbookingsystem.dto.requestDto.BookDose2RequestDto;
import com.example.vaccinationbookingsystem.model.Dose;
import com.example.vaccinationbookingsystem.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;


    //by using DTO
    @PostMapping("/get_dose_1")
    public ResponseEntity getDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto){


        try {
            Dose doseTaken = doseService.getDose1(bookDose1RequestDto);
            return new ResponseEntity(doseTaken,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // without using DTO

//    @PostMapping("/get_dose_1")
//    public ResponseEntity getDose1(@RequestParam("id") int personId, @RequestParam("doseType")DoseType doseType){
//
//
//        try {
//            Dose doseTaken = doseService.getDose2(personId,doseType);
//            return new ResponseEntity(doseTaken,HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }

    @PostMapping("/get_dose_2")
    public ResponseEntity getDose2(@RequestBody BookDose2RequestDto bookDose2RequestDto){

        try{
            Dose doseTaken = doseService.getDose2(bookDose2RequestDto);
            return new ResponseEntity(doseTaken,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
