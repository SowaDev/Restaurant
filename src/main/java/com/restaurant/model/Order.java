package com.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.enums.DeliveryStatus;
import com.restaurant.security.User;
import lombok.*;
import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @Valid
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

//    @Valid
    @ManyToOne
    @JoinColumn(name = "personaldata_id")
    private PersonalData personalData;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OrderDetails orderDetails;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public Order(Address address, PersonalData personalData, Cart cart, OrderDetails orderDetails, User user, DeliveryStatus deliveryStatus) {
        this.address = address;
        this.personalData = personalData;
        this.cart = cart;
        this.orderDetails = orderDetails;
        this.user = user;
        this.deliveryStatus = deliveryStatus;
    }

//    public void setOrderDetails(OrderDetails orderDetails) {
//        if (orderDetails == null) {
//            if (this.orderDetails != null) {
//                this.orderDetails.setOrder(null);
//            }
//        }
//        else {
//            orderDetails.setOrder(this);
//        }
//        this.orderDetails = orderDetails;
//    }
//
//    private Order(OrderBuilder orderBuilder){
//        this.address = orderBuilder.address;
//        this.personalData = orderBuilder.personalData;
//        this.orderDetails = orderBuilder.orderDetails;
//        this.deliveryStatus = orderBuilder.deliveryStatus;
//    }
//
//    public static class OrderBuilder{
//        //Required
//        private final PersonalData personalData;
//        private final DeliveryStatus deliveryStatus;
//        private final OrderDetails orderDetails;
//        private final Cart cart;
//        //Optional
//        private Address address;
//
//        public OrderBuilder(PersonalData personalData, OrderDetails orderDetails, Cart cart){
//            this.personalData = personalData;
//            this.orderDetails = orderDetails;
//            this.deliveryStatus = DeliveryStatus.ORDERED;
//            this.cart = cart;
//        }
//
//        public OrderBuilder withAddress(Address address){
//            this.address = address;
//            return this;
//        }
//
//        public Order build(){
//            return new Order(this);
//        }
//
//    }
}
