package com.example.kinopoisk.model.entities.organisation;

import com.example.kinopoisk.model.entities.country.Country;
import com.example.kinopoisk.model.entities.link.Link;
import com.example.kinopoisk.model.entities.person.Director;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.sql.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public class Organisation {
    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    private Date foundationDate;
    private String description;
    @OneToOne
    private Country country;
    @OneToOne
    private Link link;
    @ManyToMany
    private List<Director> directors;
}
