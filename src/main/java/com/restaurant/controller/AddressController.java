package com.restaurant.controller;

import com.restaurant.exception.InputNotValidException;
import com.restaurant.model.Address;
import com.restaurant.repositories.AddressRepository;
import com.restaurant.security.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<Address> getAllAddresses(){
        return this.addressRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<Address> createNewAddress(@RequestBody @Valid Address address,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new InputNotValidException(bindingResult);
        this.addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }
}
