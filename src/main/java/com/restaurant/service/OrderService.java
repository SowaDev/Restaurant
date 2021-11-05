package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.repositories.OrderRepository;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Order createNewOrder(Order order){
        return this.orderRepository.save(order);
    }

}
