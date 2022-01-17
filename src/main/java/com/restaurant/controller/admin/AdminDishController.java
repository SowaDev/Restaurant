package com.restaurant.controller.admin;

import com.restaurant.enums.DishCategory;
import com.restaurant.model.Dish;
import com.restaurant.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.restaurant.enums.DishCategory.*;

@RestController
@RequestMapping("/admin/menu")
public class AdminDishController {

    private final DishService dishService;

    public AdminDishController(DishService dishService) {
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

    @PostMapping
    public Dish createNewDish(@RequestBody Dish dish){
        return this.dishService.createNewDish(dish);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Dish was updated successfully")
    public Dish updateDish(@PathVariable("id") Long id, @RequestBody Dish dish){
        return this.dishService.updateDish(id, dish);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Dish was deleted successfully")
    public Dish deleteDish(@PathVariable("id") Long id){
        return this.dishService.deleteDish(id);
    }
}
