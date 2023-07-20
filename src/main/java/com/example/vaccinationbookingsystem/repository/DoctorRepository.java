package com.example.vaccinationbookingsystem.repository;

import com.example.vaccinationbookingsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    // query to write the code;
    @Query(value = "select * from doctor where age > :ages",nativeQuery = true) // :ages --> take the age variable from functions
    List<Doctor> getDoctorGreaterThan(int ages);
}
