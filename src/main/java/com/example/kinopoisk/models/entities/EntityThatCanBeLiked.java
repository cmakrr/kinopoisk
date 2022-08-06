package com.example.kinopoisk.models.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "entities_that_can_be_liked")
@Getter
@Setter
public class EntityThatCanBeLiked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Formula("(select count(*) from likes l where l.entity_id = id)")
    private Long likesCount;
    @OneToMany(mappedBy = "entity")
    private List<Like> likes;
}
