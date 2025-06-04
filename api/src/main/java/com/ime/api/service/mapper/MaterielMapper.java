package com.ime.api.service.mapper;

import org.mapstruct.Mapper;
import com.ime.api.model.Materiel;
import com.ime.api.service.dto.MaterielDTO;

@Mapper(componentModel = "spring")
public interface MaterielMapper {
    MaterielDTO toDto(Materiel materiel);
    Materiel toEntity(MaterielDTO dto);
}
