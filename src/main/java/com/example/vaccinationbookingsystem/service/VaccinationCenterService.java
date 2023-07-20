package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.requestDto.CenterRequestDto;
import com.example.vaccinationbookingsystem.dto.responseDto.CenterResponseDto;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.VaccinationsCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class VaccinationCenterService {
    @Autowired
    VaccinationsCenterRepository centerRepository;
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {

        // dto to entity
        VaccinationCenter center = new VaccinationCenter();

        center.setCenterName(centerRequestDto.getCenterName());
        center.setAddress(centerRequestDto.getAddress());
        center.setCenterType(centerRequestDto.getCenterType());

        //save in database
        VaccinationCenter savedCenter = centerRepository.save(center);

        // entity to dto for responseDto
        CenterResponseDto centerResponseDto = new CenterResponseDto();

        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setAddress(savedCenter.getAddress());
        centerResponseDto.setCenterType(savedCenter.getCenterType());

        return centerResponseDto;

    }
}
