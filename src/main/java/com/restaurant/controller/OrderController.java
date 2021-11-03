package com.restaurant.controller;

import com.restaurant.model.Order;
import com.restaurant.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    @GetMapping
//    public Iterable<Order> getInProgressOrders(){
//        return this.orderRepository.checkInProgress();
//    }

    @GetMapping("/all")
    public Iterable<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Order was successfully made")
    public Order createNewOrder(@RequestBody Order order){
        return this.orderRepository.save(order);
    }




}
