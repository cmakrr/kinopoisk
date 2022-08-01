package com.example.kinopoisk.controllers.profile;

import com.example.kinopoisk.models.dtos.UserDTO;
import com.example.kinopoisk.models.entities.User;
import com.example.kinopoisk.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = ProfileController.class)
class ProfileControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void myProfile() throws Exception{
        UserDTO loggedUserDTO = new UserDTO();
        Long currentUserId = 1L;
        loggedUserDTO.setId(currentUserId);

        when(userService.receiveCurrentUserId()).thenReturn(currentUserId);
        when(userService.isCurrentUser(currentUserId)).thenReturn(true);
        when(userService.findDTOById(currentUserId)).thenReturn(Optional.of(loggedUserDTO));

        mockMvc.perform(get("/profile/my_profile").with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/profile"))
                .andExpect(model().attribute("isOwner",true))
                .andExpect(model().attribute("user",loggedUserDTO));
    }

    @Test
    void profileById_ProfileExists() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        String url = String.format("/profile/%d",userDTO.getId());
        boolean isCurrentUser = false;


        when(userService.findDTOById(userDTO.getId())).thenReturn(Optional.of(userDTO));
        when(userService.isCurrentUser(userDTO.getId())).thenReturn(isCurrentUser);


        mockMvc.perform(get(url).with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/profile"))
                .andExpect(model().attribute("isOwner",isCurrentUser))
                .andExpect(model().attribute("user",userDTO));
    }

    @Test
    void profileById_ProfileDoesNotExist_ReturnErrorView() throws Exception{
        Long userId = 1L;
        String url = String.format("/profile/%d",userId);

        when(userService.findDTOById(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get(url).with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
    }

    @Test
    void editProfileById_NotCurrentUser_ReturnView() throws Exception{
        Long id = 1L;
        String url = String.format("/profile/edit/%d",id);

        when(userService.isCurrentUser(id)).thenReturn(false);

        mockMvc.perform(get(url).with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
    }

    @Test
    void editProfileById_CurrentUser() throws Exception{
        UserDTO user = new UserDTO();
        user.setUsername("validUsername");
        user.setId(1L);
        String url = String.format("/profile/edit/%d",user.getId());

        when(userService.isCurrentUser(user.getId())).thenReturn(true);
        when(userService.findDTOById(user.getId())).thenReturn(Optional.of(user));

        mockMvc.perform(get(url).with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/profileEdit"))
                .andExpect(model().attribute("user",user));
    }

    @Test
    void editProfileByIdPost_NoErrors() throws Exception{
        UserDTO user = new UserDTO();
        user.setUsername("validUsername");
        user.setId(1L);
        String url = String.format("/profile/edit/%d",user.getId());

        when(userService.isNewUsernameAppropriate(user)).thenReturn(true);

        mockMvc.perform(post(url).with(csrf()).with(user(new User()))
                .flashAttr("userDTO",user))
                .andExpect(status().isOk())
                .andExpect(view().name("profile/profileEdit"));

        verify(userService).updateUserByDTO(user);
    }

    @Test
    void deleteById() throws Exception{
        Long id = 1L;
        String url = String.format("/profile/delete/%d",id);

        mockMvc.perform(post(url).with(csrf()).with(user(new User())))
                .andExpect(status().is3xxRedirection());

        verify(userService).deleteUser(id);
    }
}