package com.restaurant.model;

import com.restaurant.enums.DeliveryStatus;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
//@ToString
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Address address;
    @ManyToOne
    private PersonalData personalData;
    @OneToOne
    private OrderDetails orderDetails;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private Order(OrderBuilder orderBuilder){
        this.address = orderBuilder.address;
        this.personalData = orderBuilder.personalData;
        this.orderDetails = orderBuilder.orderDetails;
        this.deliveryStatus = orderBuilder.deliveryStatus;
    }

    public static class OrderBuilder{
        //Required
        private final PersonalData personalData;
        private  final DeliveryStatus deliveryStatus;
        private final OrderDetails orderDetails;
        //Optional
        private Address address;

        public OrderBuilder(PersonalData personalData, OrderDetails orderDetails){
            this.personalData = personalData;
            this.orderDetails = orderDetails;
            this.deliveryStatus = DeliveryStatus.ORDERED;
        }

        public OrderBuilder withAddress(Address address){
            this.address = address;
            return this;
        }

        public Order build(){
            return new Order(this);
        }

    }
}
