package com.example.kinopoisk.logic.dtoConverters;

public interface EntityToDTOConverter<T,DTO> {
    DTO convertToDTO(T obj);
}
