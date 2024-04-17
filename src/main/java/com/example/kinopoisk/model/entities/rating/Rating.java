package com.example.kinopoisk.model.entities.rating;

import com.example.kinopoisk.model.entities.show.Product;
import com.example.kinopoisk.model.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ratings")
@Getter
@Setter
public class Rating {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Min(0)
    @Max(10)
    private int rating;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn
    private User user;
}
