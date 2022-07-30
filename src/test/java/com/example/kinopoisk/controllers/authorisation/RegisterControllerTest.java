package com.example.kinopoisk.controllers.authorisation;

import com.example.kinopoisk.models.entities.User;
import com.example.kinopoisk.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = RegisterController.class)
class RegisterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void register() throws Exception{
        mockMvc.perform(get("/register").with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(view().name("authorisation/registration"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void registerPost_PasswordAndUsernameAreInvalid() throws Exception{
        mockMvc.perform(post("/register").with(csrf()).with(user(new User()))
                        .requestAttr("user",new User()))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("user","username","password"))
                .andExpect(view().name("authorisation/registration"));
    }

    @Test
    void registerPost_UsernameIsNotUnique() throws Exception{
        User user = new User();
        user.setUsername("notUniqueUsername");
        user.setPassword("goodPassword");

        when(userService.isUsernameUnique(user.getUsername())).thenReturn(false);
        mockMvc.perform(post("/register").with(csrf()).with(user(user))
                        .flashAttr("user",user))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasFieldErrors("user","username"))
                .andExpect(view().name("authorisation/registration"));
    }

    @Test
    void registerPost_WithoutErrors() throws Exception{
        User user = new User();
        user.setUsername("goodName");
        user.setPassword("goodPassword");

        when(userService.isUsernameUnique(user.getUsername())).thenReturn(true);
        mockMvc.perform(post("/register").with(csrf()).with(user(user))
                        .flashAttr("user",user))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().errorCount(0));
        verify(userService).saveNewUser(user);
    }
}