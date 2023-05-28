package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OwnerRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
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
        userServicePort.saveOwner( userRequestMapper.toOwner(userRequestDto) );
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

    @Override
    public void saveClient( UserRequestDto userRequestDto) {
        userServicePort.saveClient( userRequestMapper.toClient( userRequestDto ) );
    }

}
