package com.restaurant.controller;

import com.restaurant.model.Address;
import com.restaurant.model.Order;
import com.restaurant.model.OrderDetails;
import com.restaurant.model.PersonalData;
import com.restaurant.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private Object OrderDetails;

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
    public Order createNewOrder(@RequestBody @Valid OrderDetails orderDetails,
                                @RequestBody @Valid Address address,
                                @RequestBody @Valid PersonalData personalData){
        return this.orderService.createNewOrder(orderDetails, address, personalData);
    }
}
