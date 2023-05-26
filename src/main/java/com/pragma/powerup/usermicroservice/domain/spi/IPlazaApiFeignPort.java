package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;


public interface IPlazaApiFeignPort {

    RestaurantResponseDto findRestaurantById( Long id, String authorizationHeader );

    void saveEmployeeByRestaurant( Long idEmployee, Long idRestaurant, String authorizationHeader );
}
