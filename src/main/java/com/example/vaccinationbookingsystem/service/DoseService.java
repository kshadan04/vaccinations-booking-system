package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Enum.DoseType;
import com.example.vaccinationbookingsystem.dto.requestDto.BookDose1RequestDto;
import com.example.vaccinationbookingsystem.dto.requestDto.BookDose2RequestDto;
import com.example.vaccinationbookingsystem.exceptions.Dose1NotTaken;
import com.example.vaccinationbookingsystem.exceptions.DoseAlreadyTaken;
import com.example.vaccinationbookingsystem.exceptions.PersonNotFoundExceptions;
import com.example.vaccinationbookingsystem.model.Dose;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.repository.DoseRepository;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {


    @Autowired
    PersonRepository personRepository;

    public Dose getDose1(BookDose1RequestDto bookDose1RequestDto) {

        Optional<Person> optionalPerson = personRepository.findById(bookDose1RequestDto.getPersonId());

        //person present or not
        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundExceptions("Invalid personId");
        }

        //person found
        Person person = optionalPerson.get();

        //dose 1 taken or not
        if(person.isDose1Taken()){
            throw  new DoseAlreadyTaken("dose1 already taken");
        }


        // give the dose
        Dose dose = new Dose();

        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose1Taken(true);
        person.getDoseTake().add(dose);
        Person savedPerson = personRepository.save(person);

        return savedPerson.getDoseTake().get(0);

    }

    public Dose getDose2(BookDose2RequestDto bookDose2RequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDose2RequestDto.getPersonId());

        // check person exist or not
        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundExceptions("Invalid personId");
        }

        // person found
        Person person = optionalPerson.get();
        // check person dose1 taken or not
        if(!person.isDose1Taken()){
            throw new Dose1NotTaken("Please take dose1 first");
        }
        else if(person.isDose1Taken() && person.isDose2Taken()){
            throw new DoseAlreadyTaken("dose2 already taken");
        }

        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose2RequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose2Taken(true);
        person.getDoseTake().add(dose);
        Person savedPerson = personRepository.save(person);

        return savedPerson.getDoseTake().get(1);
    }
}
