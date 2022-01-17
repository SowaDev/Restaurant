package com.restaurant.controller;

import com.restaurant.enums.DishCategory;
import com.restaurant.model.Dish;
import com.restaurant.service.DishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.restaurant.enums.DishCategory.*;

@RestController
@RequestMapping("/menu")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public Iterable<Dish> getAllDishes(){
        return this.dishService.getAllDishes();
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable Long id){
        return this.dishService.getDishById(id);
    }

    @GetMapping("/bycategory/{category}")
    public Iterable<Dish> getDishesByCategory(@PathVariable String category){
        DishCategory dishCategory = switch (category) {
            case "daily" -> DAILY;
            case "main" -> MAIN;
            case "side" -> SIDE;
            case "drink" -> DRINK;
            case "alcohol" -> ALCOHOL;
            case "soup" -> SOUP;
            case "dessert" -> DESSERT;
            default -> throw new IllegalStateException("Unexpected value: " + category);
        };
        return this.dishService.getDishesByCategory(dishCategory);
    }
}