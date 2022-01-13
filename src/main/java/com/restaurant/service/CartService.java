package com.restaurant.service;

import com.restaurant.model.Cart;
import com.restaurant.model.CartItem;
import com.restaurant.model.Dish;
import com.restaurant.repositories.CartItemRepository;
import com.restaurant.repositories.CartRepository;
import com.restaurant.security.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final DishService dishService;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, DishService dishService, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.dishService = dishService;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart addItem(Integer dishId, User activeUser) {
        Cart cart = activeUser.getCart();
        Dish dish = dishService.getDishById(dishId);
        CartItem item = getCartItemByDishId(dishId, cart);
        if(item == null) {
            item = this.cartItemRepository.save(new CartItem(dish, 1));
            cart.getCartItems().add(item);
        } else
            changeQuantity(item.getQuantity() + 1, dishId, activeUser);
        return this.cartRepository.save(cart);
    }

    public CartItem getCartItemByDishId(Integer dishId, Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item : cartItems) {
            if (Objects.equals(item.getDish().getId(), dishId))
                return item;
        }
        return null;
    }


    public Cart removeItem(Integer dishId, User activeUser) {
        Cart cart = activeUser.getCart();
        CartItem item = getCartItemByDishId(dishId, cart);
        List<CartItem> cartItems = cart.getCartItems();
        if(item != null)
            cartItems.remove(item);
        return this.cartRepository.save(cart);
    }

    public Cart changeQuantity(Integer number, Integer dishId, User activeUser) {
        Cart cart = activeUser.getCart();
        CartItem item = getCartItemByDishId(dishId, cart);
        if(item != null)
            item.setQuantity(number);
        return this.cartRepository.save(cart);
    }
}
