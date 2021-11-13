package com.restaurant.controller;

import com.restaurant.exception.InputNotValidException;
import com.restaurant.model.Address;
import com.restaurant.model.Order;
import com.restaurant.model.OrderDetails;
import com.restaurant.model.PersonalData;
import com.restaurant.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/details")
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderDetails createOrderDetails (@RequestBody OrderDetails orderDetails){
        return orderDetails;
    }

//    @PostMapping
//    @ResponseStatus(code = HttpStatus.CREATED, reason = "Order was successfully made")
//    public Order createNewOrder(@RequestBody @Valid OrderDetails orderDetails,
//                                @RequestBody @Valid Address address,
//                                @RequestBody @Valid PersonalData personalData,
//                                BindingResult bindingResult){
//        if(bindingResult.hasErrors())
//            throw new InputNotValidException(bindingResult);
//        return this.orderService.createNewOrder(orderDetails, address, personalData);
//    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Order was successfully made")
    public Order createNewOrder(@RequestBody @Valid Order order,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new InputNotValidException(bindingResult);
        return this.orderService.createNewOrder(order);
    }
}
