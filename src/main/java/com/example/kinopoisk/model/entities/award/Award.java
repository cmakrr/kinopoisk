package com.example.kinopoisk.model.entities.award;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Award {
    @GeneratedValue
    @Id
    private Long id;
    private String awardName;
    private Date awardDate;
}
