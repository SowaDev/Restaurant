package com.restaurant.security;

import com.google.common.collect.Lists;
import com.restaurant.model.Address;
import com.restaurant.model.Cart;
import com.restaurant.model.PersonalData;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.restaurant.security.Role.ADMIN;
import static com.restaurant.security.Role.CLIENT;

@Repository("fake")
public class FakeUserDaoService implements UserDao{

    private final PasswordEncoder passwordEncoder;

    public FakeUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<User> selectUserByUsername(String username) {
        return getUsers()
                .stream()
                .filter(User -> username.equals(User.getUsername()))
                .findFirst();
    }

    private List<User> getUsers(){
        Address clientAddress = new Address.AddressBuilder(
                "DowningStreet", "2332", "32-432", "Sosnondon").build();
        PersonalData clientPersonalData = new PersonalData("Kate", "Dworski",
                "katedworski@email.com", "324234123");
        List<User> users = Lists.newArrayList(
                new User(clientAddress, clientPersonalData, new Cart(), "client",
                        passwordEncoder.encode("client"),
                        CLIENT.getGrantedAuthorities(),
                        CLIENT,
                        true,
                        true,
                        true,
                        true),
                new User(null, null, new Cart(), "client2",
                        passwordEncoder.encode("client2"),
                        CLIENT.getGrantedAuthorities(),
                        CLIENT,
                        true,
                        true,
                        true,
                        true),
                new User(null, null, null, "admin",
                        passwordEncoder.encode("admin"),
                        ADMIN.getGrantedAuthorities(),
                        CLIENT,
                        true,
                        true,
                        true,
                        true)
        );

        return users;
    }
}
