package com.todo.Todo_app.repository;


import com.todo.Todo_app.model.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private Users user;

    @BeforeEach
    public void init() {
        user = Users.builder().username("test_user_a").email("test@gmail.com").password("secret").build();
    }

    @Test
    public void UserRepository_SaveAll_ReturnSavedUser() {
        Users savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        // TODO check if i can override the UUID generator
        Assertions.assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    public void UserRepository_GetAll_ReturnMoreThenOneUser() {
        Users user2 = Users.builder().username("test_user_2").email("test2@gmail.com").password("secret2").build();

        userRepository.save(user);
        userRepository.save(user2);
        List<Users> users = userRepository.findAll();

        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void UserRepository_FindByEmail_ReturnEmail() {

        userRepository.save(user);

        Assertions.assertThat(userRepository.findByEmail("test@gmail.com").isPresent()).isTrue();
        Assertions.assertThat(userRepository.findByEmail("test@gmail.com").get().getUsername()).isNotNull();
    }

    @Test
    public void UserRepository_FindByUsername_ReturnUsername() {
        userRepository.save(user);

        Assertions.assertThat(userRepository.findByUsername("test_user_a").isPresent()).isTrue();
        Assertions.assertThat(userRepository.findByUsername("test_user_a").get().getEmail()).isNotNull();
    }

    @Test
    public void UserRepository_UpdateUser_ReturnUsernameNotEmpty() {
        userRepository.save(user);
        Users user2 = userRepository.findById(user.getId()).get();
        user.setUsername("user");
        user.setEmail("test_2@gmail.com");

        Assertions.assertThat(user2.getUsername()).isNotNull();
        Assertions.assertThat(user2.getEmail()).isNotNull();
    }

    @Test
    public void UserRepository_DeleteUser_ReturnUsernameEmpty() {
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        Assertions.assertThat(userRepository.findByUsername("test_user_a")).isEmpty();
    }
}
