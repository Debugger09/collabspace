package com.ime.api.service.dto;

import java.util.Date;

import com.ime.api.model.Tache;
import com.ime.api.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListeMembreDTO {
    private Long id;
    private Date createdAt;
    private User membre;
    private Tache tache;


}
