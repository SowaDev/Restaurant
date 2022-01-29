package com.restaurant.service;

import com.restaurant.enums.DeliveryStatus;
import com.restaurant.exception.EmptyCartException;
import com.restaurant.exception.NoAddressOrPersonalsException;
import com.restaurant.exception.NoSuchElementFoundException;
import com.restaurant.model.*;
import com.restaurant.repositories.CartRepository;
import com.restaurant.repositories.OrderRepository;
import com.restaurant.security.User;
import com.restaurant.security.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Order createNewOrder(User activeUser, OrderDetails orderDetails){
        Cart cart = activeUser.getCart();
        User user = this.userRepository.findByUsername(activeUser.getUsername()).orElseThrow();
        if(cart == null || cart.getCartItems().isEmpty())
            throw new EmptyCartException();
        if(user.getAddress() == null || user.getPersonalData() == null)
            throw new NoAddressOrPersonalsException();
        Order order = createOrder(user, activeUser.getCart(), orderDetails);
        activeUser.setCart(new Cart());
        return this.orderRepository.save(order);
    }

    public Order createOrder(User user, Cart cart, OrderDetails orderDetails){
        Order order = new Order(user.getAddress(), user.getPersonalData(),
                cart, orderDetails, user, DeliveryStatus.ORDERED);
        order.getOrderDetails().setOrder(order);
        order.getAddress().setOrder(order);
        order.getPersonalData().setOrder(order);
        order.getCart().setOrder(order);
        return order;
    }

    public Order findById(Long id) {
        return this.orderRepository.findById(id).orElseThrow(() ->
                new NoSuchElementFoundException(id));
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

    public Cart getCartByOrderId(Long id) {
        Order order = findById(id);
        return order.getCart();
    }
}
