package com.pragma.powerup.usermicroservice.domain.utils;


import com.pragma.powerup.usermicroservice.domain.exceptions.FieldValidationException;
import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.pragma.powerup.usermicroservice.configuration.Constants.*;

public class UserFieldValidation {

    public static void ownerValidate(User user){
        Map<String, String> fieldValidation = new HashMap<>();

        if (!phoneIsValid(user.getPhone())) {
            fieldValidation.put(FIELD_PHONE, FIELD_VALIDATION);
        }
        if (!dniIsValid(user.getDni())) {
            fieldValidation.put(FIELD_DNI, FIELD_VALIDATION);
        }
        if (!ageIsValid(user.getBirthDate())) {
            fieldValidation.put(FIELD_BIRTHDATE, FIELD_VALIDATION);
        }
        if (fieldValidation.size()>0) {
            throw new FieldValidationException(fieldValidation);
        }

    }

    public static void employeeValidate(User user){
        Map<String, String> fieldValidation = new HashMap<>();

        if (!phoneIsValid(user.getPhone())) {
            fieldValidation.put(FIELD_PHONE, FIELD_VALIDATION);
        }
        if (!dniIsValid(user.getDni())) {
            fieldValidation.put(FIELD_DNI, FIELD_VALIDATION);
        }
        if (fieldValidation.size()>0) {
            throw new FieldValidationException(fieldValidation);
        }

    }

    public static boolean phoneIsValid(String phone){

        if (phone.length() !=13) {
            return false;
        }

        if (phone.charAt(0) != '+') {
            return false;
        }

        for (int i = 1; i < 13; i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;

    }

    public static boolean dniIsValid(String dni){

        return utils.isNumber(dni);

    }

    public static boolean ageIsValid(String birthDate){

        if (utils.calculateAge(birthDate)<18) {
            return false;
        }
        return true;
    }




}
