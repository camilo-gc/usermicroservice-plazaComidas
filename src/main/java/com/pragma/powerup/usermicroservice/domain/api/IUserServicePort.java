package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.List;

public interface IUserServicePort {

    User saveUser(User user);

    User getUserById(Long id);
}
