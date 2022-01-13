package com.restaurant.repositories;

import com.restaurant.enums.DeliveryStatus;
import com.restaurant.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByDeliveryStatus(DeliveryStatus deliveryStatus);
}
