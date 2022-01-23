package com.restaurant.security;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;

import static com.restaurant.security.Permissions.*;

@Getter
public enum Role {
    CLIENT(Sets.newHashSet(ORDER_CREATE)),
    EMPLOYEE(Sets.newHashSet(ORDER_READ, USER_READ, ORDER_CHANGE_STATUS)),
    ADMIN(Sets.newHashSet(ORDER_WRITE, ORDER_CREATE, MENU_WRITE, USER_WRITE, USER_READ, ORDER_CHANGE_STATUS));
//    @Id
//    @Column(name = "id", nullable = false)
//    private Long id;

//    @ElementCollection
    private Set<Permissions> permissions;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions(){
        return permissions;
    }


    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
