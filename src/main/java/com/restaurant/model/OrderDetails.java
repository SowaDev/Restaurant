package com.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "DETAILS")
public class OrderDetails {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<Dish> orderList;
    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;
    @Column(name = "DATE")
    @CreationTimestamp
    private Date date;
    @Column(name = "PICKUP")
    private Boolean isPickedUpByClient;
    @Column(name = "COMMENT")
    private String comment;
    @OneToOne
    @MapsId
    private Order order;

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
