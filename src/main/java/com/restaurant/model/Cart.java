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

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    private double totalPrice;

//    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
//    @JsonBackReference
    @JsonIgnore
    private User user;

    @OneToOne(mappedBy = "cart")
    @JsonIgnore
    private Order order;

    public void addCartItem(CartItem cartItem){
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public void removeCartItem(CartItem cartItem){
        cartItems.remove(cartItem);
        cartItem.setCart(null);
    }

    public double getTotalPrice() {
        int totalPrice = 0;
        for(CartItem item: cartItems){
            totalPrice += item.getQuantity() * item.getDish().getPrice();
        }
        return totalPrice;
    }
}
