package com.ime.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ime.api.model.Fichier;

public interface FichierRepository extends JpaRepository<Fichier, Long> {

}
