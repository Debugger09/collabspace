package com.ime.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ime.api.model.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

}
