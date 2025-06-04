package com.ime.api.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ListeMaterielDTO {
    private Long id;
    private Double quantite;
    private Date createdAt;
    private TacheDTO tache;
    private MaterielDTO materiel;
}
