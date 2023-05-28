package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.FieldValidationException;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwnerNotAuthorizedException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IJwtProviderConfigurationPort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlazaApiFeignPort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    private IUserPersistencePort userPersistencePort;
    private IUserServicePort userServicePort;
    private IJwtProviderConfigurationPort jwtProviderConfigurationPort;
    private IPlazaApiFeignPort plazaApiFeignPort;


    @BeforeEach
    void setUp() {
        this.userPersistencePort = mock(IUserPersistencePort.class);
        this.plazaApiFeignPort = mock(IPlazaApiFeignPort.class);
        this.jwtProviderConfigurationPort = mock(IJwtProviderConfigurationPort.class);
        this.userServicePort = new UserUseCase(userPersistencePort, plazaApiFeignPort, jwtProviderConfigurationPort);
    }


    @Test
    void saveOwnerFieldValidation() {

        User user = new User(
                null,
                "Pepito",
                "Perez",
                "000",
                "55555",
                "01010101",
                "pepitoperez@gmail.com",
                "$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6",
                new Role(2L, null, null));

        assertThrows(FieldValidationException.class, () -> userServicePort.saveOwner(user));
    }

    @Test
    void saveOwnerAlreadyExists() {

        User user = new User(
                null,
                "Pepito",
                "Perez",
                "111",
                "+555555555555",
                "01-01-0101",
                "pepitoperez@gmail.com",
                "$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6",
                new Role(2L, null, null));

        doThrow(UserAlreadyExistsException.class).when(userPersistencePort).saveUser(user);
        assertThrows(UserAlreadyExistsException.class, () -> userServicePort.saveOwner(user));
    }

    @Test
    void saveOwnerSuccessful() {

        User userReq = new User(
                null,
                "Pepito",
                "Perez",
                "111",
                "+555555555555",
                "01-01-0101",
                "pepitoperez@gmail.com",
                "1111",
                new Role(2L, null, null));

        User userRes = new User(
                1L,
                "Pepito",
                "Perez",
                "111",
                "+555555555555",
                "01-01-0101",
                "pepitoperez@gmail.com",
                "$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6",
                new Role(2L, null, null));


        when(userPersistencePort.saveUser(userReq)).thenReturn(userRes);
        assertNotNull(userServicePort.saveOwner(userReq));

    }


    @Test
    void getUserNotFound() {

        doThrow(UserNotFoundException.class).when(userPersistencePort).getUserById(anyLong());
        assertThrows(UserNotFoundException.class, () -> userServicePort.getUserById(anyLong()));

    }

    @Test
    void getUserByIdSuccessful() {

        User userRes = new User(
                1L,
                "Pepito",
                "Perez",
                "111",
                "+555555555555",
                "01-01-0101",
                "pepitoperez@gmail.com",
                "$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6",
                new Role(2L, null, null));

        when(userPersistencePort.getUserById(anyLong())).thenReturn(userRes);
        assertNotNull(userPersistencePort.getUserById(anyLong()));
    }


    @Test
    void saveEmployeeRestaurantNotFoundException(){

        User user = new User( null, "Pepito", "Perez", "111",
                "+555555555555", "01-01-0101", "pepitoperez@gmail.com",
                "$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6",
                new Role(2L, null, null));

        doThrow(RestaurantNotFoundException.class).when(plazaApiFeignPort).findRestaurantById(0L, "token");
        assertThrows(RestaurantNotFoundException.class, ()-> userServicePort.saveEmployee(user, "token", "0"));

    }

    @Test
    void saveEmployeeOwnerException(){

        String token = "Bearer $2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6";
        User employee = new User( null, "Pepito", "Perez", "111",
                "+555555555555", "01-01-0101", "pepitoperez@gmail.com",
                "$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6",
                new Role(2L, null, null));
        RestaurantResponseDto restaurant = new RestaurantResponseDto( 1L, "pepe food", "string", "2",
                "+793247501667", "http://pepefood.com/recursos/logo.jpg", "111" );

        when(plazaApiFeignPort.findRestaurantById(1L, token)).thenReturn(restaurant);
        when(jwtProviderConfigurationPort.getIdFromToken(token.substring(7))).thenReturn("6");

        assertThrows(OwnerNotAuthorizedException.class, ()-> userServicePort.saveEmployee(employee, token,  "1"));

    }

    @Test
    void saveEmployeeAlreadyExists() {

        String token = "Bearer $2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6";
        User employee = new User( 1L, "Pepito", "Perez", "111",
                "+555555555555", "01-01-0101", "pepitoperez@gmail.com",
                "$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6",
                new Role(2L, null, null));
        RestaurantResponseDto restaurant = new RestaurantResponseDto( 1L, "pepe food", "string",
                "2", "+793247501667", "http://pepefood.com/recursos/logo.jpg", "111" );

        when(plazaApiFeignPort.findRestaurantById(2L, token)).thenReturn(restaurant);
        when(jwtProviderConfigurationPort.getIdFromToken(token.substring(7))).thenReturn("2");

        doThrow(UserAlreadyExistsException.class).when(userPersistencePort).saveUser(employee);
        assertThrows(UserAlreadyExistsException.class, () -> userServicePort.saveEmployee(employee, token, "2"));
    }

    @Test
    void saveEmployeeSuccessful() {

        String token = "Bearer $2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6";
        User employee = new User( 1L, "Pepito", "Perez", "111",
                "+555555555555", "01-01-0101", "pepitoperez@gmail.com",
                "$2a$10$2edn/0De4Lk2IovglOz8fuC8z3b7FsctfiotMd9LMRitQnUgyPOW6",
                new Role(2L, null, null));
        RestaurantResponseDto restaurant = new RestaurantResponseDto( 1L, "pepe food", "string",
                "2", "+793247501667", "http://pepefood.com/recursos/logo.jpg", "111" );

        when(plazaApiFeignPort.findRestaurantById(2L, token)).thenReturn(restaurant);
        when(jwtProviderConfigurationPort.getIdFromToken(token.substring(7))).thenReturn("2");
        when(userPersistencePort.saveUser(employee)).thenReturn(employee);

        plazaApiFeignPort.saveEmployeeByRestaurant(employee.getId(), restaurant.getId(), token);

        verify(plazaApiFeignPort).saveEmployeeByRestaurant(employee.getId(), restaurant.getId(), token);
        assertNotNull(userServicePort.saveEmployee(employee, token, "2"));

    }


    @Test
    void saveClientAlreadyExists() {

        User user = new User( null, "Pepito", "Perez", "111", "+555555555555",
                null, "pepitoperez@gmail.com", "1111",
                new Role(2L, null, null));

        doThrow(UserAlreadyExistsException.class).when(userPersistencePort).saveUser(user);
        assertThrows(UserAlreadyExistsException.class, () -> userServicePort.saveClient(user));
    }

    @Test
    void saveClientSuccessful() {

        User user = new User( null, "Pepito", "Perez", "111", "+555555555555",
                "01-01-0101", "pepitoperez@gmail.com", "1111",
                new Role(2L, null, null));

        when(userPersistencePort.saveUser(user)).thenReturn(user);
        assertNotNull(userServicePort.saveClient(user));

    }

}
