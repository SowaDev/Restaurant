package com.restaurant.model;

import com.restaurant.enums.Category;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
//@ToString
@Entity
@Table(name = "MENU")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "PRICE")
    private Double price;

    @ManyToMany
    @JoinTable(name = "menu_orders",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "order_id"))
    private List<Order> order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dish dish = (Dish) o;
        return id != null && Objects.equals(id, dish.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
