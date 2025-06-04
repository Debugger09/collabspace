package com.ime.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "fichiers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String chemin; // chemin ou url du fichier

    private String type; // ex: pdf, image, docx...

    private Long taille; // taille en octets

    @CreationTimestamp
    @Column(updatable = false, name = "date_ajout")
    private Date dateAjout;

    @UpdateTimestamp
    @Column(name = "date_modif")
    private Date dateModif;

    @ManyToOne
    private Projet projet;

    @ManyToOne
    private Tache tache;
}