package com.example.vaccinationbookingsystem.model;


import com.example.vaccinationbookingsystem.Enum.CenterType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String centerName;

    @Enumerated(value = EnumType.STRING)
    CenterType centerType;

    String address;

    @OneToMany(mappedBy = "center",cascade = CascadeType.ALL)
    List<Doctor>  doctors = new ArrayList<>();
}
