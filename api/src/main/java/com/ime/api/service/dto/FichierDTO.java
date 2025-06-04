package com.ime.api.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class FichierDTO {
    private Long id;
    private String nom;
    private String chemin;
    private String type;
    private Long taille;
    private Date dateAjout;
    private Date dateModif;
    private ProjetDTO projet;
    private TacheDTO tache;
}