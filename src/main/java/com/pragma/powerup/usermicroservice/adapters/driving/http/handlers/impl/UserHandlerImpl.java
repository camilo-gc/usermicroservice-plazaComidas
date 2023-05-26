package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OwnerRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;


    @Override
    public void saveOwner(OwnerRequestDto userRequestDto) {
        User user = userRequestMapper.toOwner( userRequestDto );
        user.getRole().setId( Constants.OWNER_ROLE_ID );
        userServicePort.saveOwner( user );
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userResponseMapper.toResponse( userServicePort.getUserById( id ) );
    }

    @Override
    public void saveEmployee(EmployeeRequestDto userRequestDto, String token) {
        userServicePort.saveEmployee(
                userRequestMapper.toEmployee( userRequestDto ),
                token,
                userRequestDto.getId_restaurant()
        );
    }

}
