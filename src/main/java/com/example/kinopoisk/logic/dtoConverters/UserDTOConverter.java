package com.example.kinopoisk.logic.dtoConverters;

import com.example.kinopoisk.model.dtos.UserDTO;
import com.example.kinopoisk.model.entities.user.Role;
import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.repository.user.UserRepository;
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
        UserDTO dto = modelMapper.map(user,UserDTO.class);
        dto.setLikesCount(user.getLikes().size());
        dto.setReviewsCount(user.getReviews().size());
        dto.setRole(user.getRoles().stream().findAny().orElse(Role.USER).name());
        return dto;
    }
}
