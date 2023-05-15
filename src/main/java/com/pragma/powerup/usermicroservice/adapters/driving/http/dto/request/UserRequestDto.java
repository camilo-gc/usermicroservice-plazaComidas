package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Digits(integer = 11, fraction = 0)
    private String dni;

    @NotBlank
    @Pattern(regexp = "\\+[0-9]{12}")
    private String phone;

    @NotBlank
    private String birthDate;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @Digits(integer = 1, fraction = 0)
    private String id_role;

}
