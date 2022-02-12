package com.restaurant.controller.admin;

import com.restaurant.model.Address;
import com.restaurant.model.PersonalData;
import com.restaurant.security.Role;
import com.restaurant.security.User;
import com.restaurant.security.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.restaurant.security.Role.CLIENT;
import static com.restaurant.security.Role.EMPLOYEE;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> getUsers(){
        return this.userService.findAll();
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username){
        return (User) userService.loadUserByUsername(username);
    }

    @PostMapping("/{username}/{password}/{role}")
    public User createUser(@PathVariable("username") String username,
                           @PathVariable("password") String password,
                           @PathVariable("role") String role){
        Role userRole = switch (role){
            case "employee" -> EMPLOYEE;
            case "client" -> CLIENT;
            default -> throw new IllegalStateException("No role with such name");
        };
        return this.userService.createNewUser(username, password, userRole);
    }

    @PutMapping("/{username}/address")
    public Address changeUserAddress(@PathVariable String username,
                                     @RequestBody Address address){
        return this.userService.changeAddress(username, address);
    }

    @PutMapping("/{username}/personaldata")
    public PersonalData changeUserPersonalData (@PathVariable String username,
                                                @RequestBody PersonalData personalData){
        return this.userService.changePersonalData(username, personalData);
    }

    @DeleteMapping("/{username}")
    public User deleteUser(@PathVariable String username){
        return this.userService.deleteUser(username);
    }
}
