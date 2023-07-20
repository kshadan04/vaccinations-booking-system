package com.example.vaccinationbookingsystem.model;


import com.example.vaccinationbookingsystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(unique = true)
    String emailId;

    //special data type so we have to defined it
    @Enumerated(EnumType.STRING) //it is store in database as a char
    Gender gender;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    List<Appointment> appointmentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    VaccinationCenter center;
}
