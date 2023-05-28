package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.OwnerRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Add a new owner",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Owner created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Owner already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for user creation",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/new/owner")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> saveOwner(@Valid @RequestBody OwnerRequestDto userRequestDto) {
        userHandler.saveOwner(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.USER_CREATED_MESSAGE));
    }

    @Operation(summary = "Get a user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "401", description = "Role not allowed for user",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "404", description = "User not found with the provided id",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userHandler.getUserById(id));
    }

    @Operation(summary = "Add a new employee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Employee created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Employee already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error"))),
                    @ApiResponse(responseCode = "403", description = "Role not allowed for user creation",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/new/employee")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> saveEmployee(@Valid @RequestBody EmployeeRequestDto userRequestDto,
                                                            @RequestHeader HttpHeaders headers) {
        userHandler.saveEmployee(userRequestDto, headers.get("Authorization").get(0));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.USER_CREATED_MESSAGE));
    }

    @Operation(summary = "Add a new client",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Client created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Client already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/new/client")
    public ResponseEntity<Map<String, String>> saveClient(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.saveClient(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.USER_CREATED_MESSAGE));
    }

}
