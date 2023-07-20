package com.example.vaccinationbookingsystem.controller;


import com.example.vaccinationbookingsystem.dto.requestDto.CenterRequestDto;
import com.example.vaccinationbookingsystem.dto.responseDto.CenterResponseDto;
import com.example.vaccinationbookingsystem.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService centerService;

    @PostMapping("/add_center")
    public CenterResponseDto addCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto centerResponseDto =  centerService.addCenter(centerRequestDto);
        return centerResponseDto;
    }


}
