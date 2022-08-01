package com.example.kinopoisk.logic.dtoConverters;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class DTOConverterWithMapper<T,DTO> implements DTOConverter<T,DTO>{
    protected final ModelMapper modelMapper;
}
