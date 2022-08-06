package com.example.kinopoisk.configurations;

import com.example.kinopoisk.logic.dtoConverters.ReviewToDTOConverter;
import com.example.kinopoisk.logic.dtoConverters.UserDTOConverter;
import com.example.kinopoisk.repositories.UserRepository;
import com.example.kinopoisk.services.LikeService;
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
    public ReviewToDTOConverter reviewToDTOConverter(LikeService likeService){
        return new ReviewToDTOConverter(modelMapper(),likeService);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
