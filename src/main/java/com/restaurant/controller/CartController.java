package com.restaurant.controller;

import com.restaurant.model.Cart;
import com.restaurant.model.Dish;
import com.restaurant.security.FakeUserDaoService;
import com.restaurant.security.User;
import com.restaurant.service.CartService;
import com.restaurant.service.DishService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Cart getCart(@AuthenticationPrincipal User activeUser){
        return activeUser.getCart();
    }

    @PutMapping("/add/{dishId}")
    public Cart addItem(@PathVariable int dishId, @AuthenticationPrincipal User activeUser){
        return this.cartService.addItem(dishId, activeUser);
    }

    @PutMapping("/remove")
    public Cart removeItem(@RequestBody Dish dish, @AuthenticationPrincipal User activeUser){
        return this.cartService.removeItem(dish, activeUser);
    }

    @PutMapping("/changeQuantity/{number}")
    public Cart changeQuantity(@PathVariable int number, @RequestBody Dish dish,
                               @AuthenticationPrincipal User activeUser){
        return this.cartService.changeQuantity(number, dish, activeUser);
    }


}
