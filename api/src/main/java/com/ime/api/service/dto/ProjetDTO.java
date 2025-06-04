package com.ime.api.service.dto;

import java.util.Date;
import java.util.List;

import com.ime.api.model.User;
import com.ime.api.model.enums.StatutProjet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetDTO {
    private Long id;
    private String name;
    private String description;
    private StatutProjet status = StatutProjet.EN_COURS;
    private Date dateFin;
    private Date dateDebut;
    private float budget;
    private User chefProjet;
    private Date createdAt;
    private Date updatedAt;
    private List<TacheDTO> taches;
}
