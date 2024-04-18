package com.example.kinopoisk.model.entities.show;

import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.review.Review;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shows")
@Getter
@Setter
public class Show {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Enumerated
    private ShowType type;
    private String name;
    private String description;
    @Formula("(select avg(r.rating) from ratings r where r.show_id = id)")
    private Integer averageRating = 0;
    @ElementCollection
    @CollectionTable(name = "images_names", joinColumns = @JoinColumn(name = "show_id"))
    private List<String> imagesNames;
    @OneToMany(mappedBy = "show",cascade = CascadeType.REMOVE)
    private List<Rating> ratings;
    @OneToMany(mappedBy = "show",cascade = CascadeType.REMOVE)
    @OrderBy("date")
    private List<Review> reviews;
}
