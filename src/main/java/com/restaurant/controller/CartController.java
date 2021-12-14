package com.restaurant.controller;

import com.restaurant.security.FakeUserDaoService;
import com.restaurant.security.User;
import com.restaurant.service.DishService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final FakeUserDaoService fakeService;
    private final DishService dishService;

    public CartController(FakeUserDaoService fakeService, DishService dishService) {
        this.fakeService = fakeService;
        this.dishService = dishService;
    }

    @GetMapping
    public String getCart(@AuthenticationPrincipal User activeUser){
        return activeUser.getUsername();
    }
}
