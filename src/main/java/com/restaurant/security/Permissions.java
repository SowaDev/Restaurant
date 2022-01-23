package com.restaurant.security;

import javax.persistence.*;
import java.util.Collection;

public enum Permissions {
    MENU_WRITE("menu:write"),
    ORDER_READ("order:read"),
    ORDER_CREATE("order:create"),
    ORDER_WRITE("order:write"),
    ORDER_CHANGE_STATUS("order:change_status"),
    USER_READ("user:read"),
    USER_WRITE("user:write");
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;

    private final String permission;

/*    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/


    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
