package com.example.kinopoisk.model.entities.show;

import com.example.kinopoisk.model.entities.award.Award;
import com.example.kinopoisk.model.entities.country.Country;
import com.example.kinopoisk.model.entities.genres.Genres;
import com.example.kinopoisk.model.entities.likeableentity.EntityThatCanBeLiked;
import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.review.Review;
import com.example.kinopoisk.model.entities.tags.Tags;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "products")
@Getter
@Setter
public class Product extends EntityThatCanBeLiked {
    @Enumerated
    private ProductType type;
    private String name;
    private String description;
    @ElementCollection
    @CollectionTable(name = "images_names", joinColumns = @JoinColumn(name = "product_id"))
    private List<String> imagesNames;
    @ManyToMany
    private List<Genres> genres;
    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
    @OrderBy("date")
    private List<Review> reviews;
    @OneToMany
    private List<Award> awards;
    @ManyToOne
    private Country country;
    @ManyToMany
    private List<Tags> tags;
}
