package com.restaurant.repositories;

import com.restaurant.enums.DishCategory;
import com.restaurant.model.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
    List<Dish> findByDishCategory(DishCategory dishCategory);
}
