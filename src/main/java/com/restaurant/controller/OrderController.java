package com.restaurant.controller;

import com.restaurant.enums.DeliveryStatus;
import com.restaurant.exception.InputNotValidException;
import com.restaurant.model.Address;
import com.restaurant.model.Dish;
import com.restaurant.model.Order;
import com.restaurant.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/bystatus/{status}")
    public Iterable<Order> getOrdersByDeliveryStatus(@PathVariable("status") String status){
        DeliveryStatus deliveryStatus = switch (status) {
            case "ordered" -> DeliveryStatus.ORDERED;
            case "waiting" -> DeliveryStatus.WAITING;
            case "onTheWay" -> DeliveryStatus.ON_WAY;
            case "delivered" -> DeliveryStatus.DELIVERED;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
        return this.orderService.getOrdersByStatus(deliveryStatus);
    }

    @GetMapping("/all")
    public Iterable<Order> getAllOrders(){
        return this.orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Optional<Order> getOrderById(@PathVariable("id") Long id){
        return this.orderService.findById(id);
    }

    @GetMapping("/{id}/address")
    public Address getAddressByOrderId(@PathVariable("id") Long id) {
        return this.orderService.getAddressByOrderId(id);
    }

    @GetMapping("/{id}/dishes")
    public List<Dish> getOrderedDishesByOrderId(@PathVariable("id") Long id){
        return this.orderService.getOrderedDishesByOrderId(id);
    }

    @PutMapping("/{id}")
    public Order changeOrderStatus(@PathVariable("id") Long id,
                                   @RequestBody DeliveryStatus deliveryStatus){
        return this.orderService.changeOrderStatus(id, deliveryStatus);
    }

    @PostMapping()
    public ResponseEntity<Order> createNewOrder(@RequestBody @Valid Order order,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new InputNotValidException(bindingResult);
        order.setDeliveryStatus(DeliveryStatus.ORDERED);
        this.orderService.createNewOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
