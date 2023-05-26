package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {

    User saveOwner(User user);

    User saveEmployee( User user, String token, String idRestaurant );

    User getUserById(Long id);
}
