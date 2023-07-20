package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.dto.requestDto.DoctorRequestDto;
import com.example.vaccinationbookingsystem.dto.responseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try {
            DoctorResponseDto responseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // create the api for to find the doctor which is less than particular age

    @GetMapping("/get_doctor")
    public List<String> getDoctorGreaterThan(@RequestParam("id") int age){
        return doctorService.getDoctorGreaterThan(age);
    }
}
