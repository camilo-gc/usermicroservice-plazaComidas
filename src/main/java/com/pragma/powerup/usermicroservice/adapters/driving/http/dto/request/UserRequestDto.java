package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    private String name;
    private String surname;
    private String dni;
    private String phone;
    private String birthDate;
    private String email;
    private String password;
    private Long id_role;
}
