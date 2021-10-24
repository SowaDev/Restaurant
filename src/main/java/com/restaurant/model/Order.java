package com.restaurant.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ORDER_LIST")
    private List<Dish> orderList;
    @Column(name = "ADDRESS")
    private Address address;
    @Column(name = "PERSONAL_DATA")
    private PersonalData personalData;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

}
