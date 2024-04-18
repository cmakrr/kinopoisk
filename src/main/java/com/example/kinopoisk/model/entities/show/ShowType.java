package com.example.kinopoisk.model.entities.show;

public enum ShowType {
    FILM("film"),SERIES("tv series"),ANIME("anime series");

    private final String name;

    ShowType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
