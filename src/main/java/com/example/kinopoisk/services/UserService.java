package com.example.kinopoisk.services;

import com.example.kinopoisk.models.dtos.UserDTO;
import com.example.kinopoisk.models.entities.Role;
import com.example.kinopoisk.models.entities.User;
import com.example.kinopoisk.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@PropertySource("classpath:/properties/user.properties")
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${defaultAvatarName}")
    private String defaultAvatarName;
    @Value("${defaultUserRoleOrd}")
    private int defaultRoleOrd;

    public void saveNewUser(User user){
        addDefaultRoleToUser(user);
        setDefaultAvatarToUser(user);
        encodePassword(user);
        userRepository.save(user);
    }

    private void addDefaultRoleToUser(User user){
        Role role = Role.values()[defaultRoleOrd];
        Set<Role> rolesSet = Collections.singleton(role);
        user.setRoles(rolesSet);
    }

    private void setDefaultAvatarToUser(User user){
        user.setAvatarName(defaultAvatarName);
    }

    private void encodePassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public void updateUserByDTO(UserDTO userDTO){
        //User user = userDTOConverter.convertFromDTO(userDTO);
        //userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public boolean isUsernameUnique(String username){
        return userRepository.findByUsername(username).isEmpty();
    }
}

