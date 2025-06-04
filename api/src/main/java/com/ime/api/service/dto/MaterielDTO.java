package com.ime.api.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class MaterielDTO {
    private Long id;
    private String libelle;
    private boolean reutilisable;
    private boolean actif;
    private Double quantite;
    private Date createdAt;
    private Date updatedAt;
}