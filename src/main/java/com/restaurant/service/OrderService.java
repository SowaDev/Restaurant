package com.restaurant.service;

import com.restaurant.enums.DeliveryStatus;
import com.restaurant.model.*;
import com.restaurant.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Order createNewOrder(Order order){
        order.getOrderDetails().setOrder(order);
        this.orderRepository.save(order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    public Address getAddressByOrderId(Long id){
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if(optionalOrder.isEmpty())
            return null;
        Order order = optionalOrder.get();
        return order.getAddress();
    }

    public List<Dish> getOrderedDishesByOrderId(Long id){
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if(optionalOrder.isEmpty())
            return null;
        Order order = optionalOrder.get();
        return order.getOrderDetails().getOrderList();
    }


    public Order changeOrderStatus(Long id, DeliveryStatus deliveryStatus) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if(optionalOrder.isEmpty())
            return null;
        Order order = optionalOrder.get();
        order.setDeliveryStatus(deliveryStatus);
        return this.orderRepository.save(order);
    }

    public Iterable<Order> getOrdersByStatus(DeliveryStatus deliveryStatus) {
        return this.orderRepository.findByDeliveryStatus(deliveryStatus);
    }
}
