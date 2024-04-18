package com.example.kinopoisk.controller.show;

import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.show.Product;
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
class ProductControllerTest {
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
        Product product = new Product();

        mockMvc.perform(post("/show/create")
                .with(csrf())
                .with(user(new User()))
                .flashAttr("show", product))
                .andExpect(status().isOk())
                .andExpect(view().name("show/create"));


        verify(showService).save(product);
    }


    @Test
    void show_ShowExists() throws Exception{
        Long id = 1L;
        String url = String.format("/show/%d",id);
        Product product = new Product();
        Rating rating = new Rating();
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        when(showService.findById(id)).thenReturn(Optional.of(product));
        when(showService.receiveRatingFromCurrentUser(product)).thenReturn(Optional.of(rating));
        when(showService.receiveShowReviewsDTO(product)).thenReturn(reviewDTOList);

        mockMvc.perform(get(url)
                .with(user(new User())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("show", product))
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
        Product product = new Product();
        product.setId(1L);
        product.setName("name");
        String expectedRedirectUrl = String.format("/show/%d", product.getId());

        when(showService.findByName(product.getName())).thenReturn(Optional.of(product));

        mockMvc.perform(post("/show/find")
                .with(csrf())
                .with(user(new User())).param("showName", product.getName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(expectedRedirectUrl));
    }
}