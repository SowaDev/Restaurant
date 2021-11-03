package com.restaurant.repositories;

import com.restaurant.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
//    @Query("Select o FROM Order o WHERE " +
//    "o.deliveryStatus = com.restaurant.enums.DeliveryStatus.")
//    List<Order> checkInProgress

}
