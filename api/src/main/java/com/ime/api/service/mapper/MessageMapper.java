package com.ime.api.service.mapper;

import org.mapstruct.Mapper;
import com.ime.api.model.Message;
import com.ime.api.service.dto.MessageDTO;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDTO toDto(Message message);
    Message toEntity(MessageDTO dto);
}
