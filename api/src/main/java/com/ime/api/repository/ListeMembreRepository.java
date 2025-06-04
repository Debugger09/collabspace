package com.ime.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ime.api.model.ListeMembre;

public interface ListeMembreRepository extends JpaRepository<ListeMembre, Long> {

}
