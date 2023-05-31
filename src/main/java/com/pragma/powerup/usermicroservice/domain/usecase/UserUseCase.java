package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.utils.UserFieldValidation;


public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    public User saveOwner(User user) {

        UserFieldValidation.ownerValidate(user);

        user.setRole( new Role( Constants.OWNER_ROLE_ID, null, null ) );
        return userPersistencePort.saveUser(user);

    }

    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    public User saveEmployee(User user ) {
        user.setRole( new Role( Constants.EMPLOYEE_ROLE_ID, null, null ) );
        return userPersistencePort.saveUser(user);
    }

    public User saveClient(User user) {
        user.setRole( new Role( Constants.CLIENT_ROLE_ID, null, null ) );
        return userPersistencePort.saveUser(user);
    }


}
