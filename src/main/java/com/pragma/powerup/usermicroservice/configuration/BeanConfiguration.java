package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.PlazaApiAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.RoleMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter.UserMysqlAdapter;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IPlazaApiRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.domain.api.IRoleServicePort;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IJwtProviderConfigurationPort;
import com.pragma.powerup.usermicroservice.domain.spi.IPlazaApiFeignPort;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.usecase.RoleUseCase;
import com.pragma.powerup.usermicroservice.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final IPlazaApiRepository plazaApiRepository;
    private final IJwtProviderConfigurationPort jwtProviderConfigurationPort;

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleMysqlAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), plazaApiFeignPort(), jwtProviderConfigurationPort);
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserMysqlAdapter(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    public IPlazaApiFeignPort plazaApiFeignPort() {
        return new PlazaApiAdapter(plazaApiRepository);
    }

}
