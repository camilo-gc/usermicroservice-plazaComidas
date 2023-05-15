package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
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

        if (user.getRole().equals(Constants.OWNER_ROLE_ID)) {
            UserFieldValidation.ownerValidate(user);
        }

        //TODO: añadir validaciones segun rol
        /*
        if (user.getRole().equals(Constants.EMPLOYEE_ROLE_ID)) {
            UserFieldValidation.employeeValidate(user);
        }
        if (user.getRole().equals(Constants.CLIENT_ROLE_ID)) {
            UserFieldValidation.clientValidate(user);
        }
        */
        userPersistencePort.saveUser(user);

    }

    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }


}
