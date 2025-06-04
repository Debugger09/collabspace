package com.ime.api.service.mapper;

import org.mapstruct.Mapper;
import com.ime.api.model.Depense;
import com.ime.api.service.dto.DepenseDTO;

@Mapper(componentModel = "spring")
public interface DepenseMapper {
    DepenseDTO toDto(Depense depense);
    Depense toEntity(DepenseDTO dto);
}