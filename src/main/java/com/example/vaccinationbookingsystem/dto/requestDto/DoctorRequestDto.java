package com.example.vaccinationbookingsystem.dto.requestDto;

import com.example.vaccinationbookingsystem.Enum.Gender;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class DoctorRequestDto {

    int centerId;

    String name;

    int age;

    String emailId;

    Gender gender;


}
