package com.pragma.powerup.usermicroservice.domain.spi;

public interface IJwtProviderConfigurationPort {

    String getIdFromToken(String token);

    String getUserNameFromToken(String token);

}
