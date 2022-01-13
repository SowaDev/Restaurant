package com.restaurant.service;

import com.restaurant.enums.DeliveryStatus;
import com.restaurant.model.*;
import com.restaurant.repositories.CartRepository;
import com.restaurant.repositories.OrderRepository;
import com.restaurant.security.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public Iterable<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Order createNewOrder(User activeUser, OrderDetails orderDetails){
        Order order = new Order(activeUser.getAddress(), activeUser.getPersonalData(),
                activeUser.getCart(), orderDetails, activeUser, DeliveryStatus.ORDERED);
        this.orderRepository.save(order);
        activeUser.setCart(new Cart());
        return order;
    }

//    public Order createNewOrder(Order order){
//        order.getOrderDetails().setOrder(order); TODO: this is what you need to make orderdetails map ID
//        this.orderRepository.save(order);
//        return order;
//    }

    public Order findById(Long id) {
        Optional<Order> optionalOrder = this.orderRepository.findById(id);
        if(optionalOrder.isEmpty())
            return null;
        return optionalOrder.get();
    }

    public Address getAddressByOrderId(Long id){
        Order order = findById(id);
        return order.getAddress();
    }

    public PersonalData getPersonalsByOrderId(Long id) {
        Order order = findById(id);
        return order.getPersonalData();
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

    public Order deleteOrder(Long id) {
        Order orderToDelete = findById(id);
        this.orderRepository.delete(orderToDelete);
        return orderToDelete;
    }
}
