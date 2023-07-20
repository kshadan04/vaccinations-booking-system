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



public class Person {

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


    boolean isDose1Taken;
    boolean isDose2Taken;


    // for bidirectional

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Dose> doseTake = new ArrayList<>();

    @OneToOne(mappedBy = "person",cascade = CascadeType.ALL)
    Certificate certificate;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    List<Appointment> appointments = new ArrayList<>();

}
