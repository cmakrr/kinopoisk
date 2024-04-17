package com.example.kinopoisk.service.product.implementations;

import com.example.kinopoisk.exception.EntityNotFoundException;
import com.example.kinopoisk.logic.dtoConverters.ReviewToDTOConverter;
import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.review.Review;
import com.example.kinopoisk.model.entities.show.Product;
import com.example.kinopoisk.repository.product.ProductBaseRepository;
import com.example.kinopoisk.service.product.interfaces.ProductService;
import com.example.kinopoisk.service.user.implementations.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService{

    private final ProductBaseRepository productRepository;
    private final CustomUserService userService;
    private final ReviewToDTOConverter reviewToDTOConverter;

    @Override
    public Product findById(Long id){
         return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ReviewDTO> receiveShowReviewsDTO(Product product){
        return product.getReviews().stream()
                .map(reviewToDTOConverter::convertToDTO)
                .toList();
    }

    @Override
    public boolean isLikedByCurrentUser(Product product) {
        return userService.receiveCurrentUser()
                .getLikes()
                .stream()
                .anyMatch(like->like.getEntity().getId().equals(product.getId()));
    }

    @Override
    public Review receiveReviewFromCurrentUser(Product product) {
        return userService
                .receiveCurrentUser()
                .getReviews()
                .stream()
                .filter(review->review.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Product findByName(String name){
        return productRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }
}
