package com.restaurant.controller;

import com.restaurant.model.PersonalData;
import com.restaurant.repositories.PersonalDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personaldata")
public class PersonalDataController {
    private final PersonalDataRepository personalDataRepository;
    public PersonalDataController(PersonalDataRepository personalDataRepository){
        this.personalDataRepository = personalDataRepository;
    }
    @GetMapping()
    public Iterable<PersonalData> getAllPersonalData(){
        return this.personalDataRepository.findAll();
    }
    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Personal data was successfully added")
    public PersonalData createNewPersonalData(@RequestBody PersonalData personalData) {
        return this.personalDataRepository.save(personalData);
    }
}
