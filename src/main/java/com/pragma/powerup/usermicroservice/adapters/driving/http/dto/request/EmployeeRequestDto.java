package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EmployeeRequestDto extends UserRequestDto {

    @NotBlank(message = "must not be empty")
    @Pattern(regexp = "^[1-9]\\d*$", message = "an integer greater than 0 was expected")
    private String id_restaurant;

    public EmployeeRequestDto( String name, @NotBlank String surname,
                               @Digits(integer = 11, fraction = 0) String dni,
                               @Pattern(regexp = "\\+[0-9]{12}") String phone,
                               @Email String email,  String password, String id_restaurant) {

        super(name, surname, dni, phone, email, password);
        this.id_restaurant = id_restaurant;
    }
}
