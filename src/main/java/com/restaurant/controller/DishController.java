package com.restaurant.controller;

import com.restaurant.enums.DishCategory;
import com.restaurant.model.Dish;
import com.restaurant.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Dish getDishById(@PathVariable int id){
        return dishService.getDishById(id);
    }

    @GetMapping("/{category}")
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