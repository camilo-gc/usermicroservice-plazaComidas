package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@Getter
public class UserRequestDto {

    @NotBlank(message = "must not be empty")
    private String name;

    @NotBlank(message = "must not be empty")
    private String surname;

    @NotBlank(message = "must not be empty")
    @Digits(integer = 11, fraction = 0)
    private String dni;

    @NotBlank(message = "must not be empty")
    @Pattern(regexp = "\\+[0-9]{12}")
    private String phone;

    @NotBlank(message = "must not be empty")
    @Email
    private String email;

    @NotBlank(message = "must not be empty")
    private String password;

}


