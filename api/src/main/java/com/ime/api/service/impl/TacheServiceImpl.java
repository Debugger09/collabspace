package com.ime.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ime.api.model.Tache;
import com.ime.api.model.Projet;
import com.ime.api.repository.TacheRepository;
import com.ime.api.repository.ProjetRepository;
import com.ime.api.service.TacheService;
import com.ime.api.service.dto.TacheDTO;
import com.ime.api.service.mapper.TacheMapper;

@Service
public class TacheServiceImpl implements TacheService {
    private final TacheRepository tacheRepository;
    private final TacheMapper tacheMapper;
    private final ProjetRepository projetRepository;

    public TacheServiceImpl(TacheRepository tacheRepository, TacheMapper tacheMapper, ProjetRepository projetRepository) {
        this.tacheRepository = tacheRepository;
        this.tacheMapper = tacheMapper;
        this.projetRepository = projetRepository;
    }

    @Override
    public TacheDTO creer(TacheDTO tacheDTO) {
        Tache tache = tacheMapper.toEntity(tacheDTO);

        // Récupérer le projet complet si fourni
        if (tacheDTO.getProjet() != null && tacheDTO.getProjet().getId() != null) {
            Projet projet = projetRepository.findById(tacheDTO.getProjet().getId())
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));
            tache.setProjet(projet);
        } else {
            tache.setProjet(null);
        }

        Tache saved = tacheRepository.save(tache);
        return tacheMapper.toDto(saved);
    }

    @Override
    public List<TacheDTO> lire() {
        return tacheRepository.findAll()
                .stream()
                .map(tacheMapper::toDto)
                .toList();
    }

    @Override
    public TacheDTO modifier(Long id, TacheDTO tacheDTO) {
        Tache tache = tacheRepository.findById(id)
                .map(t -> {
                    t.setLibelle(tacheDTO.getLibelle());
                    t.setDescription(tacheDTO.getDescription());
                    t.setDateEcheance(tacheDTO.getDateEcheance());
                    t.setPriorite(tacheDTO.getPriorite());
                    t.setStatutTache(tacheDTO.getStatutTache());
                    // Charger le projet complet si fourni
                    if (tacheDTO.getProjet() != null && tacheDTO.getProjet().getId() != null) {
                        Projet projet = projetRepository.findById(tacheDTO.getProjet().getId())
                            .orElseThrow(() -> new RuntimeException("Projet introuvable"));
                        t.setProjet(projet);
                    } else {
                        t.setProjet(null);
                    }
                    return tacheRepository.save(t);
                })
                .orElseThrow(() -> new RuntimeException("Tâche introuvable !"));
        return tacheMapper.toDto(tache);
    }

    @Override
    public String supprimer(Long id) {
        tacheRepository.deleteById(id);
        return "Tâche supprimée !";
    }

    @Override
    public TacheDTO lireUn(Long id) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche introuvable avec l'ID : " + id));
        return tacheMapper.toDto(tache);
    }
}
