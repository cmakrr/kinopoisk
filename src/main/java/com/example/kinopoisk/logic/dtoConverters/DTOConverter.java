package com.example.kinopoisk.logic.dtoConverters;

public interface DTOConverter<T,DTO>{
    T convertFromDTO(DTO dto);

    DTO convertToDTO(T obj);
}
