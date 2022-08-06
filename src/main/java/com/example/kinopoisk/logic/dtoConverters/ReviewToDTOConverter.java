package com.example.kinopoisk.logic.dtoConverters;


import com.example.kinopoisk.models.dtos.ReviewDTO;
import com.example.kinopoisk.models.entities.Review;
import com.example.kinopoisk.services.LikeService;
import org.modelmapper.ModelMapper;

public class ReviewToDTOConverter extends EntityToDTOConverterWithMapper<Review, ReviewDTO> {
    private final LikeService likeService;

    public ReviewToDTOConverter(ModelMapper modelMapper,LikeService likeService) {
        super(modelMapper);
        this.likeService = likeService;
    }

    @Override
    public ReviewDTO convertToDTO(Review review) {
        ReviewDTO reviewDTO = modelMapper.map(review,ReviewDTO.class);
        boolean hasLikeFromCurrentUser = likeService.hasLikeFromCurrentUser(review);
        reviewDTO.setLikedByCurrentUser(hasLikeFromCurrentUser);
        return reviewDTO;
    }
}
