package com.example.vaccinationbookingsystem.model;

import com.example.vaccinationbookingsystem.Enum.DoseType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String doseId;

    @Enumerated(value = EnumType.STRING)
    DoseType doseType;

    @CreationTimestamp
    Date vaccinationDate;


    // dose is the child class && person class is the parent class
    // child class mapped with parent class

    @ManyToOne
    @JoinColumn
    Person person;


}
