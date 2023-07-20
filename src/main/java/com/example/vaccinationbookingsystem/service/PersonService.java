package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.requestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.dto.responseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.exceptions.PersonNotFoundExceptions;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
