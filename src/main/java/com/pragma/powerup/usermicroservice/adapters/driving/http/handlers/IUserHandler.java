package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OwnerRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;


public interface IUserHandler {

    void saveOwner(OwnerRequestDto userRequestDto);

    UserResponseDto getUserById(Long id);

    void saveEmployee(EmployeeRequestDto userRequestDto, String token);

    void saveClient(UserRequestDto userRequestDto);
}
