package com.example.kinopoisk.model.entities.award;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AwardType {
    GAME_OF_THE_YEAR("game of the year"), MOVIE_OF_THE_YEAR("movie of the year");
    private final String awardType;
}
