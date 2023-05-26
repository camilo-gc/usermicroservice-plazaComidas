package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "userApi", url = "${feign.plazaApi.url}")
public interface IPlazaApiRepository {

    @GetMapping("/restaurant/{id}")
    ResponseEntity<RestaurantResponseDto> findRestaurantById( @PathVariable("id") Long id, @RequestHeader("Authorization") String token );

}
