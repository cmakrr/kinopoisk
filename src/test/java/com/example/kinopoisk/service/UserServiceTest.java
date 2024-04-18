package com.example.kinopoisk.service;

import com.example.kinopoisk.model.entities.user.Role;
import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.repository.user.UserRepository;
import com.example.kinopoisk.service.user.implementations.CustomUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = CustomUserService.class)
@TestPropertySource("classpath:/properties/user.properties")
class UserServiceTest {
    @InjectMocks
    private CustomUserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Value("${defaultAvatarName}")
    private String defaultAvatarName;
    @Value("${defaultUserRoleOrd}")
    private int defaultRoleOrd;

    @Test
    void saveNewUser() {
        User user = new User();
        String username = "username";
        String password = "password";
        String encodedPassword = "encodedPassword";

        user.setPassword(password);
        user.setUsername(username);

        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        userService.saveNewUser(user);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());

        Set<Role> expectedRoles = getUserExpectedRolesSet();
        User actualUser = argumentCaptor.getValue();

        Assertions.assertEquals(username,actualUser.getUsername());
        Assertions.assertEquals(encodedPassword,actualUser.getPassword());
        Assertions.assertEquals(expectedRoles,actualUser.getRoles());
        Assertions.assertEquals(defaultAvatarName,actualUser.getAvatarName());
    }

    private Set<Role> getUserExpectedRolesSet(){
        Role userRole = Role.values()[defaultRoleOrd];
        return Collections.singleton(userRole);
    }

    @Test
    void isUsernameUnique_UniqueUsername() {
       String username = "username";

       when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

       boolean expected = true;
       boolean actual = userService.isUsernameUnique(username);

       Assertions.assertEquals(expected,actual);
    }

    @Test
    void isUsernameUnique_NotUniqueUsername(){
        String username = "username";

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User()));

        boolean expected = false;
        boolean actual = userService.isUsernameUnique(username);

        Assertions.assertEquals(expected,actual);
    }
}