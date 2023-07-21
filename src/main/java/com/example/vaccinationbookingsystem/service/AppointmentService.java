package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.dto.requestDto.BookAppointmentRequestDto;
import com.example.vaccinationbookingsystem.dto.responseDto.BookAppointmentResponseDto;
import com.example.vaccinationbookingsystem.dto.responseDto.CenterResponseDto;
import com.example.vaccinationbookingsystem.exceptions.DoctorNotFoundException;
import com.example.vaccinationbookingsystem.exceptions.DoseAlreadyTaken;
import com.example.vaccinationbookingsystem.exceptions.PersonNotFoundExceptions;
import com.example.vaccinationbookingsystem.model.Appointment;
import com.example.vaccinationbookingsystem.model.Doctor;
import com.example.vaccinationbookingsystem.model.Person;
import com.example.vaccinationbookingsystem.model.VaccinationCenter;
import com.example.vaccinationbookingsystem.repository.AppointmentRepository;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import com.example.vaccinationbookingsystem.repository.VaccinationsCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    VaccinationsCenterRepository centerRepository;

    public BookAppointmentResponseDto bookAppointment(BookAppointmentRequestDto bookAppointmentRequestDto) {

        // check doctor exist or not
        Optional<Doctor> optionalDoctor = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());

        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid doctorId");
        }

        // check either person exist or not
        Optional<Person> optionalPerson = personRepository.findById(bookAppointmentRequestDto.getPersonId());
        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundExceptions("Invalid personId");

        }


        Person person = optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

//        if(person.isDose1Taken() && person.isDose2Taken()){
//            throw new DoseAlreadyTaken("Dose@ already taken");
//        }

        Appointment appointment = new Appointment();
//        if(!person.isDose1Taken()){
//            appointment.setDoseNo(1);
//        }
//        if(person.isDose1Taken() && !person.isDose2Taken()){
//            appointment.setDoseNo(2);
//        }


         // dto to entity

        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);

        doctor.getAppointmentList().add(savedAppointment);
        person.getAppointments().add(savedAppointment);

        Doctor savedDoctor = savedAppointment.getDoctor();
        Person savedPerson = savedAppointment.getPerson();
        VaccinationCenter center = savedDoctor.getCenter();


        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterType(center.getCenterType());
        centerResponseDto.setCenterName(center.getCenterName());
        centerResponseDto.setAddress(center.getAddress());

        BookAppointmentResponseDto bookAppointmentResponseDto = new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setDoctorName(savedPerson.getName());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getName());
        bookAppointmentResponseDto.setAppointmentId(savedAppointment.getAppointmentId());
        bookAppointmentResponseDto.setAppointmentDate(savedAppointment.getAppointmentDate());
        bookAppointmentResponseDto.setCenterResponseDto(centerResponseDto);

        return bookAppointmentResponseDto;
    }
}
