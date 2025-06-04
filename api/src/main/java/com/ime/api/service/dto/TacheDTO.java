package com.ime.api.service.dto;

import java.util.Date;

import com.ime.api.model.Projet;
import com.ime.api.model.enums.PrioriteTache;
import com.ime.api.model.enums.StatutTache;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TacheDTO {
    private Long id;
    private String libelle;
    private String description;
    private Date dateEcheance;
    private PrioriteTache priorite;
    private StatutTache statutTache = StatutTache.A_FAIRE;
    private Projet projet;
    private Date createdAt;
    private Date updatedAt;
    

}
