package com.example.kinopoisk.configuration;

import com.example.kinopoisk.logic.dtoConverters.ReviewToDTOConverter;
import com.example.kinopoisk.logic.dtoConverters.UserDTOConverter;
import com.example.kinopoisk.repository.user.UserRepository;
import com.example.kinopoisk.service.like.implementations.CustomLikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOConvertersConfiguration {

    @Bean
    @Autowired
    public UserDTOConverter userDTOConverter(UserRepository userRepository){
        return new UserDTOConverter(modelMapper(),userRepository);
    }

    @Bean
    @Autowired
    public ReviewToDTOConverter reviewToDTOConverter(CustomLikeService likeService){
        return new ReviewToDTOConverter(modelMapper(),likeService);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
