package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.requestDto.DoctorRequestDto;
import com.example.vaccinationbookingsystem.dto.responseDto.CenterResponseDto;
import com.example.vaccinationbookingsystem.dto.responseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.exceptions.CenterNotFoundException;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import com.example.vaccinationbookingsystem.repository.VaccinationsCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationsCenterRepository centerRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {

        // check center exist or not
        Optional<VaccinationCenter> vaccinationCenterOptional = centerRepository.findById(doctorRequestDto.getCenterId());
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFoundException("sorry! center not found");
        }

        VaccinationCenter center = vaccinationCenterOptional.get();

        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDto.getName());
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setCenter(center);
        doctor.setEmailId(doctorRequestDto.getEmailId());

        // add doctor in center list
        center.getDoctors().add(doctor);

        VaccinationCenter savedCenter = centerRepository.save(center); // doctor is the child of center so it will save both doctor and center due to mapping

        // find the latest doctor;
        List<Doctor> doctors = savedCenter.getDoctors();
        Doctor latestSavedDoctor = doctors.get(doctors.size()-1);

        // entity to dto

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setAddress(savedCenter.getAddress());


        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setName(latestSavedDoctor.getName());
        doctorResponseDto.setMessage("Congratulation! Register successfully");
        doctorResponseDto.setCenterResponseDto(centerResponseDto);

        return doctorResponseDto;

    }

    public List<String> getDoctorGreaterThan(int age) {

        List<Doctor> doctors = doctorRepository.getDoctorGreaterThan(age);

//     List<Doctor> doctors = doctorRepository.findAll();

     List<String> ans = new ArrayList<>();

     for(Doctor doctor : doctors){
         ans.add(doctor.getName());
     }
     return ans;

    }
}
