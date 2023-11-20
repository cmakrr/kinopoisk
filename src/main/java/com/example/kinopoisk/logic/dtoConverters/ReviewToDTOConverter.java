package com.example.kinopoisk.logic.dtoConverters;


import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.review.Review;
import com.example.kinopoisk.service.like.implementations.CustomLikeService;
import org.modelmapper.ModelMapper;

public class ReviewToDTOConverter extends EntityToDTOConverterWithMapper<Review, ReviewDTO> {
    private final CustomLikeService likeService;

    public ReviewToDTOConverter(ModelMapper modelMapper, CustomLikeService likeService) {
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
