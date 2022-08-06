package com.example.kinopoisk.logic.dtoConverters;

public interface DTOConverter<T,DTO> extends EntityToDTOConverter<T,DTO>{
    T convertFromDTO(DTO dto);
}
