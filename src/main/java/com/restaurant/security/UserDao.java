package com.restaurant.security;

import java.util.Optional;

public interface UserDao {

    public Optional<User> selectUserByUsername(String username);
}
