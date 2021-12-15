package com.restaurant.service;

import com.restaurant.model.Cart;
import com.restaurant.model.CartItem;
import com.restaurant.model.Dish;
import com.restaurant.repositories.CartRepository;
import com.restaurant.security.User;

import java.util.List;

public class CartService {
    private final CartRepository cartRepository;
    private final DishService dishService;

    public CartService(CartRepository cartRepository, DishService dishService) {
        this.cartRepository = cartRepository;
        this.dishService = dishService;
    }

    public Cart addItem(int dishId, User activeUser) {
        Cart cart = activeUser.getCart();
        Dish dish = dishService.getDishById(dishId);
        cart.getCartItems().add(new CartItem(dish, 1));
        return cart;
    }


    public Cart removeItem(Dish dish, User activeUser) {
        Cart cart = activeUser.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(item -> item.getDish() == dish);
        return cart;
    }

    public Cart changeQuantity(int number, Dish dish, User activeUser) {
        Cart cart = activeUser.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        for(CartItem item : cartItems){
            if(item.getDish() == dish)
                item.setQuantity(number);
        }
        return cart;
    }
}
