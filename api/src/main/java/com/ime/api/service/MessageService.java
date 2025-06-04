package com.ime.api.service;

import java.util.List;

import com.ime.api.service.dto.MessageDTO;

public interface MessageService {
	MessageDTO creer(MessageDTO messageDTO);

	List<MessageDTO> lire();

	MessageDTO modifier(Long id, MessageDTO messageDTO);

	String supprimer(Long id);

	MessageDTO lireUn(Long id);
}
