package com.example.kinopoisk.services;

import com.example.kinopoisk.logic.auxiliaryClasses.AuxiliaryMethods;
import com.example.kinopoisk.logic.dtoConverters.ReviewToDTOConverter;
import com.example.kinopoisk.models.dtos.ReviewDTO;
import com.example.kinopoisk.models.entities.Rating;
import com.example.kinopoisk.models.entities.Show;
import com.example.kinopoisk.repositories.ShowRepository;
import com.example.kinopoisk.services.imagesSaving.ImageOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowService{
    private final ShowRepository showRepository;
    private final UserService userService;
    private final ReviewToDTOConverter reviewToDTOConverter;
    private final ImageOperations imageOperations;

    public void save(Show show) {
        showRepository.save(show);
    }

    public void setImages(Show show,MultipartFile[] images){
        if(!AuxiliaryMethods.areMultipartFilesEmpty(images)){
            List<String> imagesNames = imageOperations.saveImages(images);
            show.setImagesNames(imagesNames);
        }
    }

    public Optional<Show> findById(Long id){
         return showRepository.findById(id);
    }

    public List<ReviewDTO> receiveShowReviewsDTO(Show show){
        return show.getReviews().stream()
                .map(reviewToDTOConverter::convertToDTO)
                .toList();
    }

    public Optional<Rating> receiveRatingFromCurrentUser(Show show) {
        Long userId = userService.receiveCurrentUserId();
        return show.getRatings().stream()
                .filter(rating -> rating.getUser().getId().equals(userId))
                .findFirst();
    }

    public Optional<Show> findByName(String name){
        return showRepository.findByName(name);
    }
}
