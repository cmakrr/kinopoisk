package com.example.kinopoisk.model.entities.show;

public enum ProductType {
    FILM("film"),SERIES("tv series"),ANIME("anime series"), GAME("game");

    private final String name;

    ProductType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
