package com.restaurant.security;

import javax.persistence.*;
import java.util.Collection;

@Entity
public enum Permissions {
    PERMISSION1("permission1"),
    PERMISSION2("permission2"),
    PERMISSION3("permission3");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private final String permission;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
