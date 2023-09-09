package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.dto.requestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.dto.responseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

//    @PostMapping("/add")
//    public ResponseEntity addPerson(@RequestBody Person person){
//        try {
//            Person personRespons = personService.addPerson(person);
//            return new ResponseEntity(personRespons, HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try {
            AddPersonResponseDto personResponse = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(personResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail, @RequestParam("newEmail") String newEmail){
        try{
            String emailUpdate = personService.updateEmail(oldEmail,newEmail);
            return new ResponseEntity(emailUpdate,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_male")
    public List<String> getMaleByCertainAge(@RequestParam("id") int age){
        return personService.getMaleByCertainAge(age);
    }

    @GetMapping("/get_female_taken_dose2_only")
    public List<String> femaleTakenDose1AndNotDose2(){
        return personService.femaleTakenDose1AndNotDose2();
    }

    @GetMapping("/fully_vaccinated_people")
    public List<String> getAllPersonWhoIsFullyVaccinated(){
        return personService.getAllPersonWhoIsFullyVaccinated();
    }

    @GetMapping("/not_taken_single_dose")
    public List<String> getAllPersonWhoIsNotTakenSingleDose(){
        return personService.getAllPersonWhoIsNotTakenSingleDose();
    }


    @GetMapping("/get_female_with_certain_age")
    public List<String> getAllFemaleAgeGreaterThanCertainAgeOnlyDose1Taken(@RequestParam("age") int age){
        return personService.getAllFemaleAgeGreaterThanCertainAgeOnlyDose1Taken(age);
    }

    @GetMapping("/all_male_fully_vaccinated_greater_than_age")
    public List<String> getAllMaleFullyVaccinatedAndGreaterThanAge(@RequestParam("age") int age){
        return personService.getAllMaleFullyVaccinatedAndGreaterThanAge(age);
    }
}
