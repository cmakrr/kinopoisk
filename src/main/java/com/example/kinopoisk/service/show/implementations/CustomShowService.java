package com.example.kinopoisk.service.show.implementations;

import com.example.kinopoisk.logic.auxiliaryClasses.AuxiliaryMethods;
import com.example.kinopoisk.logic.dtoConverters.ReviewToDTOConverter;
import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.show.Product;
import com.example.kinopoisk.repository.show.ShowRepository;
import com.example.kinopoisk.service.show.interfaces.ShowService;
import com.example.kinopoisk.service.user.implementations.CustomUserService;
import com.example.kinopoisk.service.imagesSaving.implementations.CustomImageOperationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomShowService implements ShowService {

    private final ShowRepository showRepository;
    private final CustomUserService userService;
    private final ReviewToDTOConverter reviewToDTOConverter;
    private final CustomImageOperationsService customImageOperationsService;

    @Override
    public void save(Product product) {
        showRepository.save(product);
    }

    @Override
    public void setImages(Product product, MultipartFile[] images){
        if(!AuxiliaryMethods.areMultipartFilesEmpty(images)){
            List<String> imagesNames = customImageOperationsService.saveImages(images);
            product.setImagesNames(imagesNames);
        }
    }

    @Override
    public Optional<Product> findById(Long id){
         return showRepository.findById(id);
    }

    @Override
    public List<ReviewDTO> receiveShowReviewsDTO(Product product){
        return product.getReviews().stream()
                .map(reviewToDTOConverter::convertToDTO)
                .toList();
    }

    @Override
    public Optional<Rating> receiveRatingFromCurrentUser(Product product) {
        Long userId = userService.receiveCurrentUserId();
        return product.getRatings().stream()
                .filter(rating -> rating.getUser().getId().equals(userId))
                .findFirst();
    }

    @Override
    public Optional<Product> findByName(String name){
        return showRepository.findByName(name);
    }
}
