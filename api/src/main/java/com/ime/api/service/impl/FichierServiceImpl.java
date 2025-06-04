package com.ime.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ime.api.model.Fichier;
import com.ime.api.model.Projet;
import com.ime.api.model.Tache;
import com.ime.api.repository.FichierRepository;
import com.ime.api.repository.ProjetRepository;
import com.ime.api.repository.TacheRepository;
import com.ime.api.service.FichierService;
import com.ime.api.service.dto.FichierDTO;
import com.ime.api.service.mapper.FichierMapper;

@Service
public class FichierServiceImpl implements FichierService {
    private final FichierRepository fichierRepository;
    private final FichierMapper fichierMapper;
    private final ProjetRepository projetRepository;
    private final TacheRepository tacheRepository;

    public FichierServiceImpl(
        FichierRepository fichierRepository,
        FichierMapper fichierMapper,
        ProjetRepository projetRepository,
        TacheRepository tacheRepository
    ) {
        this.fichierRepository = fichierRepository;
        this.fichierMapper = fichierMapper;
        this.projetRepository = projetRepository;
        this.tacheRepository = tacheRepository;
    }

    @Override
    public FichierDTO creer(FichierDTO fichierDTO) {
        Fichier fichier = fichierMapper.toEntity(fichierDTO);

        // Lier au projet si besoin
        if (fichierDTO.getProjet() != null && fichierDTO.getProjet().getId() != null) {
            Projet projet = projetRepository.findById(fichierDTO.getProjet().getId())
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));
            fichier.setProjet(projet);
        } else {
            fichier.setProjet(null);
        }

        // Lier à la tâche si besoin
        if (fichierDTO.getTache() != null && fichierDTO.getTache().getId() != null) {
            Tache tache = tacheRepository.findById(fichierDTO.getTache().getId())
                .orElseThrow(() -> new RuntimeException("Tâche introuvable"));
            fichier.setTache(tache);
        } else {
            fichier.setTache(null);
        }

        Fichier saved = fichierRepository.save(fichier);
        return fichierMapper.toDto(saved);
    }

    @Override
    public List<FichierDTO> lire() {
        return fichierRepository.findAll()
                .stream()
                .map(fichierMapper::toDto)
                .toList();
    }

    @Override
    public FichierDTO modifier(Long id, FichierDTO fichierDTO) {
        Fichier fichier = fichierRepository.findById(id)
                .map(f -> {
                    f.setNom(fichierDTO.getNom());
                    f.setChemin(fichierDTO.getChemin());
                    f.setType(fichierDTO.getType());
                    f.setTaille(fichierDTO.getTaille());
                    // Mettre à jour le projet
                    if (fichierDTO.getProjet() != null && fichierDTO.getProjet().getId() != null) {
                        Projet projet = projetRepository.findById(fichierDTO.getProjet().getId())
                            .orElseThrow(() -> new RuntimeException("Projet introuvable"));
                        f.setProjet(projet);
                    } else {
                        f.setProjet(null);
                    }
                    // Mettre à jour la tâche
                    if (fichierDTO.getTache() != null && fichierDTO.getTache().getId() != null) {
                        Tache tache = tacheRepository.findById(fichierDTO.getTache().getId())
                            .orElseThrow(() -> new RuntimeException("Tâche introuvable"));
                        f.setTache(tache);
                    } else {
                        f.setTache(null);
                    }
                    return fichierRepository.save(f);
                })
                .orElseThrow(() -> new RuntimeException("Fichier introuvable !"));
        return fichierMapper.toDto(fichier);
    }

    @Override
    public String supprimer(Long id) {
        fichierRepository.deleteById(id);
        return "Fichier supprimé !";
    }

    @Override
    public FichierDTO lireUn(Long id) {
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fichier introuvable avec l'ID : " + id));
        return fichierMapper.toDto(fichier);
    }
}
