package com.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.security.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Table(name = "address")
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "street")
    //@Pattern(regexp = "[A-Za-zżźćńółęąśŻŹĆŚŁÓ]+", message = "street name is not valid")
    private String street;
    @Pattern(regexp = "\\d+[a-zA-z]?", message = "building number is not valid")
    @Column(name = "building_number")
    private String buildingNumber;
    @Pattern(regexp = "\\d*")
    @Column(name = "apartment_number")
    private String apartmentNumber;
//    @Pattern(regexp = "\\d*}")
    @Column(name = "floor")
    private Integer floor;
    @Column(name = "gate_code")
    private String gateCode;
    @Pattern(regexp = "\\d{2}-\\d{3}")
    @Column(name = "post_code")
    private String postCode;
    @Column(name = "city")
    @Pattern(regexp = "[A-Za-zżźćńółęąśŻŹĆŚŁÓ]+", message = "city name is not valid")
    private String city;
    @Column(name = "comment")
    private String comment;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private User user;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();


    public void setOrder(Order order) {
        orders.add(order);
    }

    private Address(AddressBuilder addressBuilder){
        this.street = addressBuilder.street;
        this.buildingNumber = addressBuilder.buildingNumber;
        this.apartmentNumber = addressBuilder.apartmentNumber;
        this.floor = addressBuilder.floor;
        this.gateCode = addressBuilder.gateCode;
        this.postCode = addressBuilder.postCode;
        this.city = addressBuilder.city;
        this.comment = addressBuilder.comment;
    }

    public static class AddressBuilder{
        //Required
        private final String street;
        private final String buildingNumber;
        private final String postCode;
        private final String city;
        //Optional
        private String apartmentNumber = "";
        private Integer floor = 0;
        private String gateCode = "";
        private String comment = "";

        public AddressBuilder(String street, String buildingNumber,
                              String postCode, String city){
            this.street = street;
            this.buildingNumber = buildingNumber;
            this.postCode = postCode;
            this.city = city;
        }

        public AddressBuilder setApartmentNumber(String apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public AddressBuilder setFloor(Integer floor) {
            this.floor = floor;
            return this;
        }

        public AddressBuilder setGateCode(String gateCode) {
            this.gateCode = gateCode;
            return this;
        }

        public AddressBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

}
