package com.restaurant.controller.admin;

import com.restaurant.model.Address;
import com.restaurant.model.Cart;
import com.restaurant.model.PersonalData;
import com.restaurant.security.Role;
import com.restaurant.security.User;
import com.restaurant.security.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public Iterable<User> getUsers(){
        return this.userRepository.findAll();
    }

    @PostMapping
    public User createUser(){
        Address address = new Address.AddressBuilder(
                "Lena", "123", "31-232", "Sosnowiec").build();
        PersonalData personalData = new PersonalData(
                "Pioter", "Monter", "pioter@email.com", "387473287");
        User user = new User(address, personalData, null,
                "username", passwordEncoder.encode("password"), Role.ADMIN);
        return this.userRepository.save(user);
    }
}
