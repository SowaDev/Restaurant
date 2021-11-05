package com.restaurant.controller;

import com.restaurant.model.Order;
import com.restaurant.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


//    @GetMapping
//    public Iterable<Order> getInProgressOrders(){
//        return this.orderRepository.checkInProgress();
//    }

    @GetMapping("/all")
    public Iterable<Order> getAllOrders(){
        return this.orderService.getAllOrders();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Order was successfully made")
    public Order createNewOrder(@RequestBody Order order){
        return this.orderService.createNewOrder(order);
    }
}
