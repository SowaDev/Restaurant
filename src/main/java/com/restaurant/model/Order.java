package com.restaurant.model;

import com.restaurant.enums.DeliveryStatus;
import lombok.*;
import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@ToString
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personals_id", referencedColumnName = "id")
    private PersonalData personalData;

//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id", referencedColumnName = "id")
//    private Cart cart;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Valid
    private OrderDetails orderDetails;

    @Column(name = "status")
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
        private final DeliveryStatus deliveryStatus;
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
