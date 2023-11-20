package com.example.kinopoisk.controller.show;

import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.show.Show;
import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.service.show.implementations.CustomShowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = ShowController.class)
class ShowControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomShowService showService;

    @Test
    void createShow() throws Exception{
        mockMvc.perform(get("/show/create").with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("show"))
                .andExpect(view().name("show/create"));
    }

    @Test
    void createShowPost() throws Exception{
        Show show = new Show();

        mockMvc.perform(post("/show/create")
                .with(csrf())
                .with(user(new User()))
                .flashAttr("show",show))
                .andExpect(status().isOk())
                .andExpect(view().name("show/create"));


        verify(showService).save(show);
    }


    @Test
    void show_ShowExists() throws Exception{
        Long id = 1L;
        String url = String.format("/show/%d",id);
        Show show = new Show();
        Rating rating = new Rating();
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        when(showService.findById(id)).thenReturn(Optional.of(show));
        when(showService.receiveRatingFromCurrentUser(show)).thenReturn(Optional.of(rating));
        when(showService.receiveShowReviewsDTO(show)).thenReturn(reviewDTOList);

        mockMvc.perform(get(url)
                .with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("show",show))
                .andExpect(view().name("show/show"));
    }

    @Test
    void show_ShowDoesNotExist() throws Exception{
        Long id = 1L;
        String url = String.format("/show/%d",id);

        when(showService.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get(url)
                        .with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
    }

    @Test
    void findByName() throws Exception{
        Show show = new Show();
        show.setId(1L);
        show.setName("name");
        String expectedRedirectUrl = String.format("/show/%d",show.getId());

        when(showService.findByName(show.getName())).thenReturn(Optional.of(show));

        mockMvc.perform(post("/show/find")
                .with(csrf())
                .with(user(new User())).param("showName",show.getName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(expectedRedirectUrl));
    }
}