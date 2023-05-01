package com.cpt202.appointment_system;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Optional;

import com.cpt202.appointment_system.Models.User;
import com.cpt202.appointment_system.Repositories.UserRepo;
import com.cpt202.appointment_system.Services.LoginService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoginServiceTest {

    @Mock
    private UserRepo userRepository;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUser_Success() {
        // Arrange
        String username = "user123";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setType(0);
        Optional<User> dbUser = Optional.of(user);
        when(userRepository.findByUsernameOptional(username)).thenReturn(dbUser);

        // Act
        int result = loginService.loginUser(username, password);

        // Assert
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testLoginUser_WrongPassword() {
        // Arrange
        String username = "user123";
        String password = "wrong_password";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        Optional<User> dbUser = Optional.of(user);
        when(userRepository.findByUsernameOptional(username)).thenReturn(dbUser);

        // Act
        int result = loginService.loginUser(username, password);

        // Assert
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testRegisterUser_UsernameExists() {
        // Arrange
        String username = "user123";
        User user = new User();
        user.setUsername(username);
        when(userRepository.existsByUsername(username)).thenReturn(true);

        // Act
        int result = loginService.registerUser(user);

        // Assert
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testRegisterUser_EmailExists() {
        // Arrange
        String email = "user123@example.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.existsByUsername(email)).thenReturn(false);
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        int result = loginService.registerUser(user);

        // Assert
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testRegisterUser_Success() {
        // Arrange
        String username = "user123";
        String email = "user123@example.com";
        String password = "password";
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        user.setRegistrationTime(currentTime);
        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act
        int result = loginService.registerUser(user);

        // Assert
        Assertions.assertEquals(3, result);
    }
}
