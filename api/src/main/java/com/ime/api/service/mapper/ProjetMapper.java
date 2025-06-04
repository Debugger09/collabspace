package com.ime.api.service.mapper;

import org.mapstruct.Mapper;

import com.ime.api.model.Projet;
import com.ime.api.service.dto.ProjetDTO;

@Mapper(componentModel = "spring")
public interface ProjetMapper {
    ProjetDTO toDto(Projet projet);
    Projet toEntity(ProjetDTO dto);

}
