package com.restaurant.controller;

import com.restaurant.model.Dish;
import com.restaurant.repositories.DishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class DishController {
    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @GetMapping
    public Iterable<Dish> getAllDishes(){
        return this.dishRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Dish was successfully added")
    public Dish createNewDish(@RequestBody Dish dish){
        return this.dishRepository.save(dish);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Dish was updated successfully")
    public Dish updateDish(@PathVariable("id") Integer id, @RequestBody Dish dish){
        Optional<Dish> dishToUpdateOptional = this.dishRepository.findById(id);
        if(dishToUpdateOptional.isEmpty())
            return null;
        Dish dishToUpdate = dishToUpdateOptional.get();
        if(dish.getName() != null)
            dishToUpdate.setName(dish.getName());
        if(dish.getCategory() != null)
            dishToUpdate.setCategory(dish.getCategory());
        if(dish.getPrice() != null)
            dishToUpdate.setPrice(dish.getPrice());
        return this.dishRepository.save(dishToUpdate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Dish was deleted successfully")
    public Dish deleteDish(@PathVariable("id") Integer id){
        Optional<Dish> dishToDeleteOptional = this.dishRepository.findById(id);
        if(dishToDeleteOptional.isEmpty())
            return null;
        Dish dishToDelete = dishToDeleteOptional.get();
        this.dishRepository.delete(dishToDelete);
        return dishToDelete;
    }
}
