package com.pragma.powerup.usermicroservice;

import com.pragma.powerup.usermicroservice.adapters.driving.http.controller.UserRestController;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    @InjectMocks
    private UserRestController userRestController;

    @Mock
    private IUserHandler userHandler;

    private MockMvc mockMvc;

    @BeforeEach
    void serUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    void saveOwnerController(){

        //assertEquals();

    }

}
