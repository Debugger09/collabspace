package com.ime.api.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class MessageDTO {
    private Long id;
    private UserDTO expediteur;
    private UserDTO destinataire;
    private String contenu;
    private Date createdAt;
    private Date updatedAt;
}
