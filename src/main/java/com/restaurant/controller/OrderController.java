package com.restaurant.controller;

import com.restaurant.enums.DeliveryStatus;
import com.restaurant.exception.InputNotValidException;
import com.restaurant.model.*;
import com.restaurant.security.User;
import com.restaurant.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    @PreAuthorize("hasAuthority('order:read')")
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

    @PreAuthorize("hasAuthority('order:read')")
    @GetMapping
    public Iterable<Order> getAllOrders(){
        return this.orderService.getAllOrders();
    }

    @PreAuthorize("hasAuthority('order:read')")
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id){
        return this.orderService.findById(id);
    }

    @PreAuthorize("hasAuthority('order:read')")
    @GetMapping("/{id}/address")
    public Address getAddressByOrderId(@PathVariable("id") Long id) {
        return this.orderService.getAddressByOrderId(id);
    }

    @PreAuthorize("hasAuthority('order:read')")
    @GetMapping("/{id}/personaldata")
    public PersonalData getPersonalsByOrderId(@PathVariable("id") Long id) {
        return this.orderService.getPersonalsByOrderId(id);
    }

    @PreAuthorize("hasAuthority('order:change_status')")
    @PutMapping("/{id}")
    public Order changeOrderStatus(@PathVariable("id") Long id,
                                   @RequestBody DeliveryStatus deliveryStatus){
        return this.orderService.changeOrderStatus(id, deliveryStatus);
    }

    @PreAuthorize("hasAuthority('order:create')")
    @PostMapping()
    public ResponseEntity<Order> createNewOrder(@AuthenticationPrincipal User activeUser,
                                                @RequestBody OrderDetails orderDetails){
        Order newOrder = this.orderService.createNewOrder(activeUser, orderDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @PreAuthorize("hasAuthority('order:write')")
    @DeleteMapping("/{id}")
    public Order deleteOrder(@PathVariable("id") Long id){
        return this.orderService.deleteOrder(id);
    }

    /*    @PostMapping()
    public ResponseEntity<Order> createNewOrder(@RequestBody @Valid Order order,
                                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new InputNotValidException(bindingResult);
        order.setDeliveryStatus(DeliveryStatus.ORDERED);
        //this.orderService.createNewOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }*/

}
