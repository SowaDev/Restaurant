package com.restaurant.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "order")
    private final List<Dish> orderList;
    private final Double totalPrice;
    private final Date date;
    private final Boolean isPickedUpByClient;
    private final String comment;

    private OrderDetails(OrderDetailsBuilder orderDetailsBuilder) {
        this.orderList = orderDetailsBuilder.orderList;
        this.totalPrice = orderDetailsBuilder.totalPrice;
        this.date = orderDetailsBuilder.date;
        this.isPickedUpByClient = orderDetailsBuilder.isPickedUpByClient;
        this.comment = orderDetailsBuilder.comment;
    }


    public static class OrderDetailsBuilder{
        private final List<Dish> orderList;
        private final Double totalPrice;
        private final Date date;
        private final Boolean isPickedUpByClient;
        private String comment = "";

        public OrderDetailsBuilder(List<Dish> orderList, Double totalPrice,
                                   Date date, Boolean isPickedUpByClient) {
            this.orderList = orderList;
            this.totalPrice = totalPrice;
            this.date = date;
            this.isPickedUpByClient = isPickedUpByClient;
        }

        public OrderDetailsBuilder withComment(String comment){
            this.comment = comment;
            return this;
        }

        public OrderDetails build(){
            return new OrderDetails(this);
        }
    }
}
