package com.restaurant.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private PersonalData personalData;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders;

    private String username;
    private String password;
//    @ElementCollection
//    private Set<? extends GrantedAuthority> grantedAuthorities;
    @Enumerated(EnumType.STRING)
//    @ManyToOne
//    @JoinColumn(name = "role_id")
    private Role role;
//    private boolean isAccountNonExpired;
//    private boolean isAccountNonLocked;
//    private boolean isCredentialsNonExpired;
//    private boolean isEnabled;

    public User() {
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(Address address, PersonalData personalData,
                Cart cart, String username,
                String password,
//                Set<? extends GrantedAuthority> grantedAuthorities,
                Role role)
//                boolean isAccountNonExpired,
//                boolean isAccountNonLocked,
//                boolean isCredentialsNonExpired,
//                boolean isEnabled) {
    {
        this.address = address;
        this.personalData = personalData;
        this.cart = cart;
        this.username = username;
        this.password = password;
        this.role = role;
//        this.grantedAuthorities = grantedAuthorities;
//        this.isAccountNonExpired = isAccountNonExpired;
//        this.isAccountNonLocked = isAccountNonLocked;
//        this.isCredentialsNonExpired = isCredentialsNonExpired;
//        this.isEnabled = isEnabled;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Address getAddress(){
        return address;
    }

    public void setAddress(Address address){
        this.address = address;
    }

    public PersonalData getPersonalData(){
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
