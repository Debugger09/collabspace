package com.ime.api.service.mapper;

import org.mapstruct.Mapper;
import com.ime.api.model.ListeMateriel;
import com.ime.api.service.dto.ListeMaterielDTO;

@Mapper(componentModel = "spring")
public interface ListeMaterielMapper {
    ListeMaterielDTO toDto(ListeMateriel listeMateriel);
    ListeMateriel toEntity(ListeMaterielDTO dto);
}
