package com.ime.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ime.api.model.Tache;

public interface TacheRepository extends JpaRepository<Tache, Long> {

}
