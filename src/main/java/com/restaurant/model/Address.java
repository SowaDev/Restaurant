package com.restaurant.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String buildingNumber;
    private String apartmentNumber;
    private Integer floor;
    private String gateCode;
    private String postCode;
    private String city;
    private String comment;

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
