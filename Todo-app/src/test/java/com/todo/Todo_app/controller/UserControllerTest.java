package com.todo.Todo_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.Todo_app.api.controller.auth.AuthController;
import com.todo.Todo_app.dto.RegistrationDTO;
import com.todo.Todo_app.repository.UserRepository;
import com.todo.Todo_app.service.impl.JWTServiceImp;
import com.todo.Todo_app.service.impl.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImp userService;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JWTServiceImp jwtService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void AuthController_RegisterUser_ReturnCreated() throws Exception {
        RegistrationDTO userRegistration = RegistrationDTO.builder().username("user_dot").email("dto@email.com").password("dto_secret").build();

        given(userService.registerUser(ArgumentMatchers.any()))
                .willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc
                .perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegistration)));


        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }

}
