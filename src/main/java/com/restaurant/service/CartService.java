package com.restaurant.service;

import com.restaurant.model.Cart;
import com.restaurant.model.CartItem;
import com.restaurant.model.Dish;
import com.restaurant.security.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartService {
    private final DishService dishService;

    public CartService(DishService dishService) {
        this.dishService = dishService;
    }

    public Cart addItem(Long dishId, User activeUser) {
        if(activeUser.getCart() == null)
            activeUser.setCart(new Cart());
        Cart cart = activeUser.getCart();
        Dish dish = dishService.getDishById(dishId);
        CartItem item = getCartItemByDishId(dishId, cart);
        if(item == null)
            cart.addCartItem(new CartItem(dish, 1));
        else
            changeQuantity(item.getQuantity() + 1, dishId, activeUser);
        return cart;
    }

    public CartItem getCartItemByDishId(Long dishId, Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item : cartItems) {
            if (Objects.equals(item.getDish().getId(), dishId))
                return item;
        }
        return null;
    }


    public Cart removeItem(Long dishId, User activeUser) {
        Cart cart = activeUser.getCart();
        CartItem item = getCartItemByDishId(dishId, cart);
        List<CartItem> cartItems = cart.getCartItems();
        if(item != null)
            cartItems.remove(item);
        return cart;
    }

    public Cart changeQuantity(Integer number, Long dishId, User activeUser) {
        Cart cart = activeUser.getCart();
        CartItem item = getCartItemByDishId(dishId, cart);
        if(item != null)
            item.setQuantity(number);
        return cart;
    }
}
