package com.restaurant.model;

import com.restaurant.enums.DeliveryStatus;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
//    @ManyToMany(mappedBy = "order")
//    private List<Dish> orderList;
//    @Column(name = "COMMENT")
//    private String comment;
//    @Column(name = "TOTAL_PRICE")
//    private Double totalPrice;
//    @Column(name = "PICK_UP")
//    private Boolean isPickedUpByClient;
//    @Column(name = "DATE")
//    private Date date;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private Order(OrderBuilder orderBuilder){
//        this.orderList = orderBuilder.orderList;
        this.address = orderBuilder.address;
        this.personalData = orderBuilder.personalData;
        this.orderDetails = orderBuilder.orderDetails;
//        this.comment = orderBuilder.comment;
//        this.totalPrice = orderBuilder.totalPrice;
        this.deliveryStatus = orderBuilder.deliveryStatus;
//        this.isPickedUpByClient = orderBuilder.isPickedUpByClient;
//        this.date = orderBuilder.date;
    }

    public static class OrderBuilder{
        //Required
//        private final List<Dish> orderList;
        private final PersonalData personalData;
//        private final Double totalPrice;
        private  final DeliveryStatus deliveryStatus;
        private final OrderDetails orderDetails;
//        private final Boolean isPickedUpByClient;
//        private final Date date;
        //Optional
        private Address address;
//        private String comment;

//        public OrderBuilder (List<Dish> orderList, PersonalData personalData, Double totalPrice,
//                             DeliveryStatus deliveryStatus, Boolean isPickedUpByClient, Date date){
//            this.orderList = orderList;
//            this.personalData = personalData;
//            this.totalPrice = totalPrice;
//            this.deliveryStatus = deliveryStatus;
//            this.isPickedUpByClient = isPickedUpByClient;
//            this.date = date;
//        }

        public OrderBuilder(PersonalData personalData, OrderDetails orderDetails){
            this.personalData = personalData;
            this.orderDetails = orderDetails;
            this.deliveryStatus = DeliveryStatus.ORDERED;
        }

        public OrderBuilder withAddress(Address address){
            this.address = address;
            return this;
        }

//        public OrderBuilder withComment(String comment){
//            this.comment = comment;
//            return this;
//        }

        public Order build(){
            return new Order(this);
        }

    }
}
