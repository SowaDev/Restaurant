package com.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DISH_ID")
    private Dish dish;

    private Integer quantity;

//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    @JsonIgnore
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    public CartItem(Dish dish, int quantity){
        this.dish = dish;
        this.quantity = quantity;
    }

}
