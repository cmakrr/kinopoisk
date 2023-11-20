package com.example.kinopoisk.service.user.implementations;

import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record UserDetailsServiceImplementation(UserRepository userRepository) implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        } else{
            throw new UsernameNotFoundException("user with such name was not found");
        }
    }
}
