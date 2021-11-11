package com.restaurant.service;

import com.restaurant.model.Address;
import com.restaurant.model.Order;
import com.restaurant.model.OrderDetails;
import com.restaurant.model.PersonalData;
import com.restaurant.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Order createNewOrder(OrderDetails orderDetails,
                                Address address, PersonalData personalData){
        Order order;
        if(orderDetails.getIsPickedUpByClient())
            order = new Order.OrderBuilder(personalData, orderDetails).withAddress(address).build();
        else
            order = new Order.OrderBuilder(personalData, orderDetails).build();
        return this.orderRepository.save(order);
    }
}
