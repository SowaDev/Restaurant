package com.restaurant.repositories;

import com.restaurant.model.Address;
import com.restaurant.model.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
}
