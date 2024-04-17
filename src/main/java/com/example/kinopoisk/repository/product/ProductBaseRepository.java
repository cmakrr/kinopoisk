package com.example.kinopoisk.repository.product;

import com.example.kinopoisk.model.entities.show.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductBaseRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    boolean existsById(Long id);
}
