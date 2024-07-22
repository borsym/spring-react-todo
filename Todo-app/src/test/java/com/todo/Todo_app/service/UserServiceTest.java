package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.LoginDTO;
import com.todo.Todo_app.dto.RegistrationDTO;
import com.todo.Todo_app.exception.UserAlreadyExistsException;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private EncryptionService encryptionService;
    @Mock
    private JWTService jwtService;

    @InjectMocks
    private UserService userService;
    private final String username = "test_user";
    private final String password = "secret";
    private final String encryptedPassword = "encrypted_secret";
    private final String jwtToken = "jwt_token";

    @Test
    public void UserService_RegisterUser_ReturnUsers() throws UserAlreadyExistsException {
        Users user = Users.builder().username("test_user_a").email("test@gmail.com").password("secret").build();

        RegistrationDTO userRegistration = RegistrationDTO.builder().username("user_dot").email("dto@email.com").password("dto_secret").build();
        when(encryptionService.encryptPassword(userRegistration.getPassword())).thenReturn("encrypted_password");
        when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);
        // TODO check if return type is good, should i change it instead to some DTO?
        Users registeredUser = userService.registerUser(userRegistration);
        Assertions.assertThat(registeredUser).isNotNull();
    }

    @Test
    public void UserService_GetAllUsers_ReturnUsers() {
        Users user1 = Users.builder().username("test_user_a").email("test@gmail.com").password("secret").build();
        Users user2 = Users.builder().username("test_user_b").email("test2@gmail.com").password("secret2").build();
        List<Users> userList = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<Users> result = userService.getAllUsers();

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(user1, user2);
    }

    @Test
    public void UserService_GetUserById_ReturnUser() {
        UUID userId = UUID.randomUUID();
        Users user = Users.builder()
                .id(userId)
                .username("test_user")
                .email("test@gmail.com")
                .password("secret")
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<Users> result = userService.getUserById(userId);

        Assertions.assertThat(result).isPresent();
        Assertions.assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void UserService_GetUserById_ReturnEmpty() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<Users> result = userService.getUserById(userId);

        Assertions.assertThat(result).isNotPresent();
    }


    @Test
    public void UserService_LoginUser_ReturnsJwtToken() {
        Users user = Users.builder()
                .username(username)
                .password(encryptedPassword)
                .build();
        LoginDTO loginDTO = LoginDTO.builder()
                .username(username)
                .password(password).build();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(encryptionService.verifyPassword(password, encryptedPassword)).thenReturn(true);
        when(jwtService.generateJWT(user)).thenReturn(jwtToken);

        String result = userService.loginUser(loginDTO);

        Assertions.assertThat(result).isEqualTo(jwtToken);
    }

    @Test
    public void UserService_LoginUser_ReturnsNull_WhenUserNotFound() {
        LoginDTO loginDTO = LoginDTO.builder().username(username).password(password).build();

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        String result = userService.loginUser(loginDTO);

        Assertions.assertThat(result).isNull();
    }

    @Test
    public void UserService_LoginUser_ReturnsNull_WhenPasswordVerificationFails() {
        Users user = Users.builder().username(username).password(encryptedPassword).build();
        LoginDTO loginDTO = LoginDTO.builder().username(username).password(password).build();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(encryptionService.verifyPassword(password, encryptedPassword)).thenReturn(false);

        String result = userService.loginUser(loginDTO);

        Assertions.assertThat(result).isNull();
    }


}
