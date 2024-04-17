package com.example.kinopoisk.model.entities.organisation;

import com.example.kinopoisk.model.entities.show.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Publisher extends Organisation {
    @OneToMany
    private List<Product> products;
}
