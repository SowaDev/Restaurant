package com.restaurant.service;

import com.restaurant.enums.DishCategory;
import com.restaurant.model.Dish;
import com.restaurant.repositories.DishRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Iterable<Dish> getAllDishes(){
        return this.dishRepository.findAll();
    }

    public Dish createNewDish(Dish dish){
        return this.dishRepository.save(dish);
    }

    public Dish updateDish(Integer id, Dish dish){
        Optional<Dish> dishToUpdateOptional = this.dishRepository.findById(id);
        if(dishToUpdateOptional.isEmpty())
            return null;
        Dish dishToUpdate = dishToUpdateOptional.get();
        if(dish.getName() != null)
            dishToUpdate.setName(dish.getName());
        if(dish.getDishCategory() != null)
            dishToUpdate.setDishCategory(dish.getDishCategory());
        if(dish.getPrice() != null)
            dishToUpdate.setPrice(dish.getPrice());
        return this.dishRepository.save(dishToUpdate);
    }

    public Dish deleteDish(Integer id){
        Optional<Dish> dishToDeleteOptional = this.dishRepository.findById(id);
        if(dishToDeleteOptional.isEmpty())
            return null;
        Dish dishToDelete = dishToDeleteOptional.get();
        this.dishRepository.delete(dishToDelete);
        return dishToDelete;
    }


    public Iterable<Dish> getDishesByCategory(DishCategory dishCategory) {
        return this.dishRepository.findByDishCategory(dishCategory);
    }

    public Dish getDishById(@PathVariable int id) {
        Optional<Dish> optionalDish = this.dishRepository.findById(id);
        if(optionalDish.isEmpty())
            return null;
        return optionalDish.get();
    }
}
