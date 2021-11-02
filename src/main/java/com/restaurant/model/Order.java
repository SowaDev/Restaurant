package com.restaurant.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
//@ToString
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(mappedBy = "order")
    private List<Dish> orderList;
    @OneToOne
    private Address address;
    @OneToOne
    private PersonalData personalData;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;
}
