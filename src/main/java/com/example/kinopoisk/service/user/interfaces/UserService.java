package com.example.kinopoisk.service.user.interfaces;

import com.example.kinopoisk.model.dtos.UserDTO;
import com.example.kinopoisk.model.entities.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserService {

    void saveNewUser(User user);

    void setAvatar(UserDTO user, MultipartFile avatar);

    Long receiveCurrentUserId();

    User receiveCurrentUser();

    boolean isCurrentUser(Long id);

    Optional<User> findUserById(Long id);

    Optional<UserDTO> findDTOById(Long id);

    void updateUserByDTO(UserDTO userDTO);

    void deleteUser(Long id);

    boolean isNewUsernameAppropriate(UserDTO userDTO);

    boolean isUsernameUnique(String username);
}
