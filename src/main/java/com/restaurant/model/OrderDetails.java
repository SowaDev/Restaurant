package com.restaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DETAILS")
public class OrderDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE")
    @CreationTimestamp
    private Date date;

    @Column(name = "PICKUP")
    private Boolean isPickedUpByClient;

    @Column(name = "COMMENT")
    private String comment;

    @OneToOne(mappedBy = "orderDetails")
    @JsonIgnore
    private Order order;

    private OrderDetails(OrderDetailsBuilder orderDetailsBuilder) {
        this.date = orderDetailsBuilder.date;
        this.isPickedUpByClient = orderDetailsBuilder.isPickedUpByClient;
        this.comment = orderDetailsBuilder.comment;
    }


    public static class OrderDetailsBuilder{
        private final Date date;
        private final Boolean isPickedUpByClient;
        private String comment = "";

        public OrderDetailsBuilder(Date date, Boolean isPickedUpByClient) {
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
