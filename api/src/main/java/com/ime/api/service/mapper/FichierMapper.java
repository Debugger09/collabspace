package com.ime.api.service.mapper;

import org.mapstruct.Mapper;
import com.ime.api.model.Fichier;
import com.ime.api.service.dto.FichierDTO;

@Mapper(componentModel = "spring")
public interface FichierMapper {
    FichierDTO toDto(Fichier fichier);
    Fichier toEntity(FichierDTO dto);
}
