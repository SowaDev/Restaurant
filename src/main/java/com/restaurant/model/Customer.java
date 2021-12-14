package com.restaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.restaurant.security.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personals_id", referencedColumnName = "id")
    private PersonalData personalData;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    @JsonManagedReference
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "")
    private User user;
}
