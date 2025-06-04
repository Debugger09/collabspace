package com.ime.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ime.api.model.Depense;
import com.ime.api.model.Projet;
import com.ime.api.repository.DepenseRepository;
import com.ime.api.repository.ProjetRepository;
import com.ime.api.service.DepenseService;
import com.ime.api.service.dto.DepenseDTO;
import com.ime.api.service.mapper.DepenseMapper;

@Service
public class DepenseServiceImpl implements DepenseService {
    private final DepenseRepository depenseRepository;
    private final DepenseMapper depenseMapper;
    private final ProjetRepository projetRepository;

    public DepenseServiceImpl(DepenseRepository depenseRepository, DepenseMapper depenseMapper, ProjetRepository projetRepository) {
        this.depenseRepository = depenseRepository;
        this.depenseMapper = depenseMapper;
        this.projetRepository = projetRepository;
    }

    @Override
    public DepenseDTO creer(DepenseDTO depenseDTO) {
        Depense depense = depenseMapper.toEntity(depenseDTO);

        // Récupérer le projet complet
        if (depenseDTO.getProjet() != null && depenseDTO.getProjet().getId() != null) {
            Projet projet = projetRepository.findById(depenseDTO.getProjet().getId())
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));
            depense.setProjet(projet);
        } else {
            depense.setProjet(null);
        }

        Depense saved = depenseRepository.save(depense);
        return depenseMapper.toDto(saved);
    }

    @Override
    public List<DepenseDTO> lire() {
        return depenseRepository.findAll()
                .stream()
                .map(depenseMapper::toDto)
                .toList();
    }

    @Override
    public DepenseDTO modifier(Long id, DepenseDTO depenseDTO) {
        Depense depense = depenseRepository.findById(id)
                .map(d -> {
                    d.setLibelle(depenseDTO.getLibelle());
                    d.setMontant(depenseDTO.getMontant());
                    // Mettre à jour le projet
                    if (depenseDTO.getProjet() != null && depenseDTO.getProjet().getId() != null) {
                        Projet projet = projetRepository.findById(depenseDTO.getProjet().getId())
                            .orElseThrow(() -> new RuntimeException("Projet introuvable"));
                        d.setProjet(projet);
                    } else {
                        d.setProjet(null);
                    }
                    return depenseRepository.save(d);
                })
                .orElseThrow(() -> new RuntimeException("Dépense introuvable !"));
        return depenseMapper.toDto(depense);
    }

    @Override
    public String supprimer(Long id) {
        depenseRepository.deleteById(id);
        return "Dépense supprimée !";
    }

    @Override
    public DepenseDTO lireUn(Long id) {
        Depense depense = depenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dépense introuvable avec l'ID : " + id));
        return depenseMapper.toDto(depense);
    }
}
