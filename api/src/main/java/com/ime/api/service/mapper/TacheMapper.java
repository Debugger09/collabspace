package com.ime.api.service.mapper;

import org.mapstruct.Mapper;
import com.ime.api.model.Tache;
import com.ime.api.service.dto.TacheDTO;

@Mapper(componentModel = "spring")
public interface TacheMapper {
    TacheDTO toDto(Tache tache);
    Tache toEntity(TacheDTO dto);
}
