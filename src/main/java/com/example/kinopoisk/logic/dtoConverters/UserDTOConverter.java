package com.example.kinopoisk.logic.dtoConverters;

import com.example.kinopoisk.models.dtos.UserDTO;
import com.example.kinopoisk.models.entities.User;
import com.example.kinopoisk.repositories.UserRepository;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class UserDTOConverter extends DTOConverterWithMapper<User, UserDTO> implements  EntityToDTOConverter<User,UserDTO> {
    private final UserRepository userRepository;

    public UserDTOConverter(ModelMapper modelMapper, UserRepository userRepository) {
        super(modelMapper);
        this.userRepository = userRepository;
    }

    @Override
    public User convertFromDTO(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Optional<User> oldUser = userRepository.findById(userDTO.getId());
        oldUser.ifPresent(value->{
            user.setPassword(value.getPassword());
            user.setRoles(value.getRoles());
        });
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user,UserDTO.class);
    }
}
