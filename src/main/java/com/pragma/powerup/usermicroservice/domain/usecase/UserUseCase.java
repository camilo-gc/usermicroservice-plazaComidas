package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwnerNotAuthorizedException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IJwtProviderConfigurationPort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlazaApiFeignPort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.utils.UserFieldValidation;


public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPlazaApiFeignPort plazaApiFeignPort;
    private final IJwtProviderConfigurationPort jwtProviderConfigurationPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPlazaApiFeignPort plazaApiFeignPort,
                       IJwtProviderConfigurationPort jwtProviderConfigurationPort) {
        this.userPersistencePort = userPersistencePort;
        this.plazaApiFeignPort = plazaApiFeignPort;
        this.jwtProviderConfigurationPort = jwtProviderConfigurationPort;
    }

    public User saveOwner(User user) {

        UserFieldValidation.ownerValidate(user);

        user.setRole( new Role( Constants.OWNER_ROLE_ID, null, null ) );
        return userPersistencePort.saveUser(user);

    }

    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    public User saveEmployee(User user, String token, String idRestaurant ) {

        RestaurantResponseDto restaurant = plazaApiFeignPort.findRestaurantById( Long.valueOf(idRestaurant), token );
        String idOwner = restaurant.getId_owner();
        String idUser = jwtProviderConfigurationPort.getIdFromToken(token.substring(7));

        if (!idUser.equals( idOwner ) ) {
            throw new OwnerNotAuthorizedException();
        }

        user.setRole( new Role( Constants.EMPLOYEE_ROLE_ID, null, null ) );
        User employee = userPersistencePort.saveUser(user);
        plazaApiFeignPort.saveEmployeeByRestaurant( employee.getId(), restaurant.getId(), token);

        return employee;

    }

    public User saveClient(User user) {
        user.setRole( new Role( Constants.CLIENT_ROLE_ID, null, null ) );
        return userPersistencePort.saveUser(user);
    }


}
