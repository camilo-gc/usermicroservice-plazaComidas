package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UnauthorizedOwnerValidationException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IPlazaApiRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.domain.spi.IPlazaApiFeignPort;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

@RequiredArgsConstructor
@CommonsLog
public class PlazaApiAdapter implements IPlazaApiFeignPort {

    private final IPlazaApiRepository plazaApiRepository;

    public RestaurantResponseDto findRestaurantById(Long id, String token) {

        ResponseEntity<RestaurantResponseDto> responseEntity = null;

        try {

            responseEntity = plazaApiRepository.findRestaurantById( id, token );

        } catch ( FeignException e ){

            if ( e.status() == 401 ) {
                log.error( "Feign: 401 -> Find Unauthorized" );
                throw new UnauthorizedOwnerValidationException();
            }
            if ( e.status() == 404 ) {
                log.error( "Feign: 404 -> Restaurant not found" );
                throw new RestaurantNotFoundException();
            }
            if ( e.status() == 500 ) {
                log.error( "Feign: 500 -> PlazaApi internal error" );
                throw new HttpServerErrorException(HttpStatusCode.valueOf(e.status()));
            }
            if ( e.status() == -1 ) {
                log.error( "Feign: -1 -> PlazaApi not available" );
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        return responseEntity.getBody();
        
    }

    public void saveEmployeeByRestaurant( Long idEmployee, Long idRestaurant, String authorizationHeader ){
        // TODO document why this method is empty
    }

}
