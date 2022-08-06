package com.example.kinopoisk.services;

import com.example.kinopoisk.logic.dtoConverters.UserDTOConverter;
import com.example.kinopoisk.models.dtos.UserDTO;
import com.example.kinopoisk.models.entities.Role;
import com.example.kinopoisk.models.entities.User;
import com.example.kinopoisk.repositories.UserRepository;
import com.example.kinopoisk.services.imagesSaving.ImageOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
@PropertySource("classpath:/properties/user.properties")
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDTOConverter userDTOConverter;
    private final ImageOperations imageOperations;
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

    public void setAvatar(UserDTO user, MultipartFile avatar){
        if(!avatar.isEmpty()){
            Optional<String> avatarName = imageOperations.saveImage(avatar);
            avatarName.ifPresent(user::setAvatarName);
        }
    }

    public Long receiveCurrentUserId(){
        return receiveCurrentUser().getId();
    }

    public User receiveCurrentUser(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isCurrentUser(Long id){
        return receiveCurrentUserId().equals(id);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<UserDTO> findDTOById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(userDTOConverter::convertToDTO);
    }

    public void updateUserByDTO(UserDTO userDTO){
        User user = userDTOConverter.convertFromDTO(userDTO);
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public boolean isNewUsernameAppropriate(UserDTO userDTO){
        Optional<User> userWithNewName = userRepository.findByUsername(userDTO.getUsername());
        return userWithNewName.isEmpty() || userWithNewName.get().getId().equals(userDTO.getId());
    }

    public boolean isUsernameUnique(String username){
        return userRepository.findByUsername(username).isEmpty();
    }
}

