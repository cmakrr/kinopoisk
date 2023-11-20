package com.example.kinopoisk.service.show.interfaces;

import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.show.Show;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ShowService {

    void save(Show show);

    void setImages(Show show, MultipartFile[] images);

    Optional<Show> findById(Long id);

    List<ReviewDTO> receiveShowReviewsDTO(Show show);

    Optional<Rating> receiveRatingFromCurrentUser(Show show);

    Optional<Show> findByName(String name);
}
