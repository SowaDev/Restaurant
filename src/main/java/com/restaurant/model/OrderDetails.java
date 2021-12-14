package com.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DETAILS")
public class OrderDetails {
    @Id
    @Column(name = "order_id")
 //   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @OneToMany
//    @JoinTable(
//            name = "MENU",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "dish_id")
//    )
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> orderList;
//    @Column(name = "TOTAL_PRICE")
//    private Double totalPrice;
    @Column(name = "DATE")
    @CreationTimestamp
    private Date date;
    @Column(name = "PICKUP")
    private Boolean isPickedUpByClient;
    @Column(name = "COMMENT")
    private String comment;
    @OneToOne
    @MapsId
    //@JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    private OrderDetails(OrderDetailsBuilder orderDetailsBuilder) {
        this.orderList = orderDetailsBuilder.orderList;
//        this.totalPrice = orderDetailsBuilder.totalPrice;
        this.date = orderDetailsBuilder.date;
        this.isPickedUpByClient = orderDetailsBuilder.isPickedUpByClient;
        this.comment = orderDetailsBuilder.comment;
    }


    public static class OrderDetailsBuilder{
        private final List<Dish> orderList;
//        private final Double totalPrice;
        private final Date date;
        private final Boolean isPickedUpByClient;
        private String comment = "";

        public OrderDetailsBuilder(List<Dish> orderList,
                                   Date date, Boolean isPickedUpByClient) {
            this.orderList = orderList;
//            this.totalPrice = totalPrice;
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
