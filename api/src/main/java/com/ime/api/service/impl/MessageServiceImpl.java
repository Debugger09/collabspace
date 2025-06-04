package com.ime.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ime.api.model.Message;
import com.ime.api.model.User;
import com.ime.api.repository.MessageRepository;
import com.ime.api.repository.UserRepository;
import com.ime.api.service.MessageService;
import com.ime.api.service.dto.MessageDTO;
import com.ime.api.service.mapper.MessageMapper;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final UserRepository userRepository;

    // Durée d’édition autorisée (ex : 15 minutes)
    private static final long EDIT_WINDOW_MS = 15 * 60 * 1000;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.userRepository = userRepository;
    }

    @Override
    public MessageDTO creer(MessageDTO messageDTO) {
        Message message = messageMapper.toEntity(messageDTO);

        // Récupérer l'expéditeur complet
        if (messageDTO.getExpediteur() != null && messageDTO.getExpediteur().getId() != null) {
            User expediteur = userRepository.findById(messageDTO.getExpediteur().getId())
                .orElseThrow(() -> new RuntimeException("Expéditeur introuvable"));
            message.setExpediteur(expediteur);
        } else {
            message.setExpediteur(null);
        }

        // Récupérer le destinataire complet
        if (messageDTO.getDestinataire() != null && messageDTO.getDestinataire().getId() != null) {
            User destinataire = userRepository.findById(messageDTO.getDestinataire().getId())
                .orElseThrow(() -> new RuntimeException("Destinataire introuvable"));
            message.setDestinataire(destinataire);
        } else {
            message.setDestinataire(null);
        }

        Message saved = messageRepository.save(message);
        return messageMapper.toDto(saved);
    }

    @Override
    public List<MessageDTO> lire() {
        return messageRepository.findAll()
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }

    @Override
    public MessageDTO modifier(Long id, MessageDTO messageDTO) {
        Message message = messageRepository.findById(id)
                .map(m -> {
                    // Vérifier la fenêtre d'édition
                    Date now = new Date();
                    if (now.getTime() - m.getCreatedAt().getTime() > EDIT_WINDOW_MS) {
                        throw new RuntimeException("Le délai d’édition du message est dépassé !");
                    }
                    m.setContenu(messageDTO.getContenu());
                    return messageRepository.save(m);
                })
                .orElseThrow(() -> new RuntimeException("Message introuvable !"));
        return messageMapper.toDto(message);
    }

    @Override
    public String supprimer(Long id) {
        messageRepository.deleteById(id);
        return "Message supprimé !";
    }

    @Override
    public MessageDTO lireUn(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message introuvable avec l'ID : " + id));
        return messageMapper.toDto(message);
    }
}
