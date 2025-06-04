package com.ime.api.model;

import java.util.*;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "liste_membre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListeMembre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @ManyToOne
    private User membre;

    @ManyToOne
    private Tache tache;
}