package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {

    User saveOwner(User user);

    User getUserById(Long id);

    User saveEmployee( User user );

    User saveClient( User user );
}
