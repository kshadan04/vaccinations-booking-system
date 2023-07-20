package com.example.vaccinationbookingsystem.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String certificateNo; //auto generated through uuid
    String confirmationMessage;

    @OneToOne
    @JoinColumn // for foreign key
    Person person;
}
