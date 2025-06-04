package com.ime.api.service.mapper;

import org.mapstruct.Mapper;
import com.ime.api.model.ListeMembre;
import com.ime.api.service.dto.ListeMembreDTO;

@Mapper(componentModel = "spring")
public interface ListeMembreMapper {
    ListeMembreDTO toDto(ListeMembre listeMembre);
    ListeMembre toEntity(ListeMembreDTO dto);
}