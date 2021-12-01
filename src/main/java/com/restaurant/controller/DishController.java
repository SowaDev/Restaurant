package com.restaurant.controller;

import com.restaurant.model.Dish;
import com.restaurant.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Dish createNewDish(@RequestBody Dish dish){
        return this.dishService.createNewDish(dish);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Dish was updated successfully")
    public Dish updateDish(@PathVariable("id") Integer id, @RequestBody Dish dish){
        return this.dishService.updateDish(id, dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Dish was deleted successfully")
    public Dish deleteDish(@PathVariable("id") Integer id){
        return this.dishService.deleteDish(id);
    }
}
