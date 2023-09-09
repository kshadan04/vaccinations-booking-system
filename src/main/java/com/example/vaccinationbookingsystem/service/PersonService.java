package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.requestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.dto.responseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.exceptions.PersonNotFoundExceptions;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        //convert DTo to entity;
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setEmailId(addPersonRequestDto.getEmailId());
        person.setGender(addPersonRequestDto.getGender());

//        person.setDose1Taken(false); // initially no dose taken by anyone
//        person.setDose2Taken(false);

        Person savedPerson = personRepository.save(person);


        // convert entity to dto;
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(person.getName());
        addPersonResponseDto.setMessage("Congratulation! you had registered for vaccination");

        return addPersonResponseDto;
    }


    public String updateEmail(String oldEmail, String newEmail) {
        Person person = personRepository.findByEmailId(oldEmail);

        if(person == null){
            throw new PersonNotFoundExceptions("email does not exist");
        }

        person.setEmailId(newEmail);
        personRepository.save(person);
        return "Congratulations!! your email updated successfully";
    }

    public List<String> getMaleByCertainAge(int age) {
        List<Person> persons = personRepository.getMaleByCertainAge(age);

        List<String> personList = new ArrayList<>();
        for(Person person : persons){
            personList.add(person.getName());
        }
        return personList;

    }


    public List<String> femaleTakenDose1AndNotDose2() {
        List<String> personList = personRepository.femaleTakenDose1AndNotDose2();
        return personList;


    }

    public List<String> getAllPersonWhoIsFullyVaccinated() {
        return personRepository.getAllPersonWhoIsFullyVaccinated();
    }

    public List<String> getAllPersonWhoIsNotTakenSingleDose() {
        return personRepository.getAllPersonWhoIsNotTakenSingleDose();
    }

//    public List<String> getAllFemaleAgeGreaterThanCertainAgeOnlyDose1Taken(int age) {
//         personRepository.getAllFemaleAgeGreaterThanCertainAgeOnlyDose1Taken(age);
//    }
}
