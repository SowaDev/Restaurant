package com.restaurant.controller.admin;

import com.restaurant.enums.DishCategory;
import com.restaurant.model.Dish;
import com.restaurant.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.restaurant.enums.DishCategory.*;

@RestController
@RequestMapping("/admin/menu")
public class AdminDishController {

    private final DishService dishService;

    public AdminDishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PreAuthorize("hasAuthority('menu:write')")
    @GetMapping
    public Iterable<Dish> getAllDishes(){
        return this.dishService.getAllDishes();
    }

    @PreAuthorize("hasAuthority('menu:write')")
    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable Long id){
        return this.dishService.getDishById(id);
    }

    @PreAuthorize("hasAuthority('menu:write')")
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

    @PreAuthorize("hasAuthority('menu:write')")
    @PostMapping
    public Dish createNewDish(@RequestBody Dish dish){
        return this.dishService.createNewDish(dish);
    }

    @PreAuthorize("hasAuthority('menu:write')")
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Dish was updated successfully")
    public Dish updateDish(@PathVariable("id") Long id, @RequestBody Dish dish){
        return this.dishService.updateDish(id, dish);
    }

    @PreAuthorize("hasAuthority('menu:write')")
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "Dish was deleted successfully")
    public Dish deleteDish(@PathVariable("id") Long id){
        return this.dishService.deleteDish(id);
    }
}
