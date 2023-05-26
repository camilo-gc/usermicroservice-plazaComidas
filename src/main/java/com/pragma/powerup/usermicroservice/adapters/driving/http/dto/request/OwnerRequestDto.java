package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
public class OwnerRequestDto extends UserRequestDto{

    @NotBlank(message = "must not be empty")
    private String birth_date;

    public OwnerRequestDto( String name, String surname,
                            @Digits(integer = 11, fraction = 0) String dni,
                            @Pattern(regexp = "\\+[0-9]{12}") String phone,
                            @Email String email, String password, String birth_date) {

        super(name, surname, dni, phone, email, password);
        this.birth_date = birth_date;

    }
}
