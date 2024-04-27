package com.example.kinopoisk.repository.show;

import com.example.kinopoisk.model.entities.show.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Product,Long> {
    boolean existsShowById(Long id);
    Optional<Product> findByName(String name);

    List<Product> findByNameContaining(String name);
}
