package com.example.kinopoisk.logic.dtoConverters;

import com.example.kinopoisk.model.dtos.UserDTO;
import com.example.kinopoisk.model.entities.user.Role;
import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDTOConverterTest {
    private static UserDTOConverter userDTOConverter;
    private static UserRepository mockedUserRepository;

    @BeforeAll
    public static void initialize(){
        mockedUserRepository = Mockito.mock(UserRepository.class);
        userDTOConverter = new UserDTOConverter(new ModelMapper(),mockedUserRepository);
    }

    @Test
    void convertFromDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setUsername("username");
        dto.setAvatarName("avatar");

        User oldUser = new User();
        oldUser.setPassword("password");
        oldUser.setRoles(Collections.singleton(Role.ADMIN));

        when(mockedUserRepository.findById(dto.getId())).thenReturn(Optional.of(oldUser));
        User user = userDTOConverter.convertFromDTO(dto);

        Assertions.assertEquals(dto.getId(),user.getId());
        Assertions.assertEquals(dto.getUsername(),user.getUsername());
        Assertions.assertEquals(dto.getAvatarName(),user.getAvatarName());
        Assertions.assertEquals(oldUser.getPassword(),user.getPassword());
        Assertions.assertEquals(oldUser.getRoles(),user.getRoles());
    }

    @Test
    void convertToDTO() {
        User user = new User();
        user.setUsername("username");
        user.setId(1L);
        user.setAvatarName("avatar");
        UserDTO dto = userDTOConverter.convertToDTO(user);

        Assertions.assertEquals(user.getId(),dto.getId());
        Assertions.assertEquals(user.getUsername(),dto.getUsername());
        Assertions.assertEquals(user.getAvatarName(),dto.getAvatarName());
    }
}