package com.pragma.powerup.usermicroservice.configuration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long ADMIN_ROLE_ID = 1L;
    public static final Long OWNER_ROLE_ID = 2L;
    public static final Long EMPLOYEE_ROLE_ID = 3L;
    public static final Long CLIENT_ROLE_ID = 4L;
    public static final int MAX_PAGE_SIZE = 2;
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String USER_CREATED_MESSAGE = "User created successfully";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found for the requested petition";
    public static final String PERSON_ALREADY_EXISTS_MESSAGE = "A person already exists with the DNI number provided";
    public static final String MAIL_ALREADY_EXISTS_MESSAGE = "A person with that mail already exists";
    public static final String PERSON_NOT_FOUND_MESSAGE = "No person found with the id provided";
    public static final String ROLE_NOT_FOUND_MESSAGE = "No role found with the id provided";
    public static final String ROLE_NOT_ALLOWED_MESSAGE = "No permission granted to create users with this role";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user already exists with the dni provided";
    public static final String USER_NOT_FOUND_MESSAGE = "No user found with the role provided";
    public static final String USER_NAME_NOT_FOUND_MESSAGE = "No user found with the username provided";
    public static final String SWAGGER_TITLE_MESSAGE = "User API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";

    public static final String FIELD_VALIDATION = "The field value is not valid";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_SURNAME = "surname";
    public static final String FIELD_DNI = "dni";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_BIRTHDATE = "birthDate";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_ID_ROLE = "id_role";


}
