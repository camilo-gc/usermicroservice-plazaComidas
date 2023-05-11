package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.utils.UserFieldValidation;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;



    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    public void saveUser(User user) {

        UserFieldValidation.validate(user);
        userPersistencePort.saveUser(user);

    }

}
