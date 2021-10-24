package com.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonalData {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
