package com.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.security.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>();

    private double totalPrice;

    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private Order order;


    public double getTotalPrice() {
        int totalPrice = 0;
        for(CartItem item: cartItems){
            totalPrice += item.getQuantity() * item.getDish().getPrice();
        }
        return totalPrice;
    }
}
