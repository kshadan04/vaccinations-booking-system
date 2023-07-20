package com.example.vaccinationbookingsystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String appointmentId;

    Date appointmentDate;

    int doseNo;

    @ManyToOne
    @JoinColumn
    Person person;

    @ManyToOne
    @JoinColumn
    Doctor doctor;
}
