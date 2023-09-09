package com.example.vaccinationbookingsystem.repository;

import com.example.vaccinationbookingsystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {


    Person findByEmailId(String oldEmail);

    @Query(value = "select * from person where age > :ages AND gender = 'MALE'",nativeQuery = true)
    List<Person> getMaleByCertainAge(int ages);

    @Query(value = "select name from person where is_Dose1Taken = true and is_Dose2Taken = false and gender = 'FEMALE' ",nativeQuery = true)
    List<String> femaleTakenDose1AndNotDose2();

    @Query(value = "select name from person where is_dose1Taken = true and is_dose2Taken = true", nativeQuery = true )
    List<String> getAllPersonWhoIsFullyVaccinated();

    @Query(value = "select name from person where is_dose1Taken = false and is_dose2Taken = false", nativeQuery = true)
    List<String> getAllPersonWhoIsNotTakenSingleDose();

    @Query(value = "select name from person where age > :ages and is_dose1Taken = true and is_dose2Taken = false", nativeQuery = true)
    List<String> getAllFemaleAgeGreaterThanCertainAgeOnlyDose1Taken(int ages);

    @Query(value = "select name from person where age > :ages and is_dose1Taken = true and is_dose2Taken = true", nativeQuery = true)
    List<String> getAllMaleFullyVaccinatedAndGreaterThanAge(int ages);
}
