package com.example.kinopoisk.repository.show;

import com.example.kinopoisk.model.entities.show.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
    boolean existsShowById(Long id);
    Optional<Show> findByName(String name);
}
