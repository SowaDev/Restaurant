package com.restaurant.controller;

import com.restaurant.exception.InputNotValidException;
import com.restaurant.model.Address;
import com.restaurant.model.PersonalData;
import com.restaurant.security.User;
import com.restaurant.security.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/personaldata")
    public PersonalData getPersonalData(@AuthenticationPrincipal User activeUser){
        return activeUser.getPersonalData();
    }

    @GetMapping("/address")
    public Address getAddress(@AuthenticationPrincipal User activeUser){
        return activeUser.getAddress();
    }

    @PostMapping("/personaldata")
    public PersonalData createPersonalData(@AuthenticationPrincipal User activeUser,
                                     @RequestBody @Valid PersonalData personalData,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new InputNotValidException(bindingResult);
        return this.userService.createPersonalData(activeUser, personalData);
    }

    @PostMapping("/address")
    public Address createUserAddress(@AuthenticationPrincipal User activeUser,
                                     @RequestBody @Valid Address address,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new InputNotValidException(bindingResult);
        return this.userService.createAddress(activeUser, address);
    }

    @PutMapping("/personaldata")
    public PersonalData changePersonalData(@AuthenticationPrincipal User activeUser,
                                           @RequestBody PersonalData personalData){
        return this.userService.changePersonalData(activeUser.getUsername(), personalData);
    }


    @PutMapping("/address")
    public Address changeUserAddress(@AuthenticationPrincipal User activeUser,
                                     @RequestBody Address address){
        return this.userService.changeAddress(activeUser.getUsername(), address);
    }

}
