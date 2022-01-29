package com.restaurant.service;

import com.restaurant.exception.NoSuchElementFoundException;
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
        Cart cart = activeUser.getCart() == null ? setNewCart(activeUser) : activeUser.getCart();
        Dish dish = dishService.getDishById(dishId);
        CartItem item = getCartItemByDishId(dishId, cart);
        return item == null? addCartItem(cart, dish) : changeQuantity(item.getQuantity() + 1, dishId, activeUser);
    }

    public Cart addCartItem(Cart cart, Dish dish){
        CartItem item = new CartItem(dish, 1);
        cart.getCartItems().add(item);
        item.setCart(cart);
        return cart;
    }


    public Cart setNewCart(User user){
        user.setCart(new Cart());
        return user.getCart();
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
        else throw new NoSuchElementFoundException(dishId);
        return cart;
    }
}
