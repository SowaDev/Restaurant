package com.restaurant.controller;

import com.restaurant.exception.InputNotValidException;
import com.restaurant.model.PersonalData;
import com.restaurant.repositories.PersonalDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/personaldata")
public class PersonalDataController {

    private final PersonalDataRepository personalDataRepository;

    public PersonalDataController(PersonalDataRepository personalDataRepository){
        this.personalDataRepository = personalDataRepository;
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<PersonalData> getAllPersonalData(){
        return this.personalDataRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Object> createNewPersonalData(@RequestBody @Valid PersonalData personalData,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new InputNotValidException(bindingResult);
        this.personalDataRepository.save(personalData);
        return ResponseEntity.status(HttpStatus.OK).body(personalData);
    }
}
