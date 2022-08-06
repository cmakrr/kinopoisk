package com.example.kinopoisk.logic.dtoConverters;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class EntityToDTOConverterWithMapper<T,DTO> implements EntityToDTOConverter<T,DTO>{
    protected final ModelMapper modelMapper;
}
