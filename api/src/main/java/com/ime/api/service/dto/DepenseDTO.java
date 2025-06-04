package com.ime.api.service.dto;

import java.util.Date;

import com.ime.api.model.Projet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepenseDTO {
    private Long id;
    private String libelle;
    private double montant;
    private Projet projet;
    private Date createdAt;
    private Date updatedAt;



}
