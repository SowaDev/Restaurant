package com.restaurant.security;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.restaurant.model.Address;
import com.restaurant.model.Cart;
import com.restaurant.model.Order;
import com.restaurant.model.PersonalData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "personals_id")
    private PersonalData personalData;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id")
//    @JsonManagedReference
    private Cart cart;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Order> orders = new HashSet<>();

    private final String username;
    private final String password;
    private final Set<? extends GrantedAuthority> grantedAuthorities;
    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;



    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(Address address, PersonalData personalData,
                Cart cart, String username,
                String password,
                Set<? extends GrantedAuthority> grantedAuthorities,
                Role role, boolean isAccountNonExpired,
                boolean isAccountNonLocked,
                boolean isCredentialsNonExpired,
                boolean isEnabled) {
        this.address = address;
        this.personalData = personalData;
        this.cart = cart;
        this.username = username;
        this.password = password;
        this.role = role;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public Cart getCart(){
        return cart;
    }

    public void setCart(Cart cart){
        this.cart = cart;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Address getAddress(){
        return address;
    }

    public PersonalData getPersonalData(){
        return personalData;
    }
}
