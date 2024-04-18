package com.example.kinopoisk.model.entities.person;

import com.example.kinopoisk.model.entities.show.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_characters")
@Getter
@Setter
public class Character extends Person {
    private String name;
    private String description;
    @ManyToOne
    private Product product;
}
