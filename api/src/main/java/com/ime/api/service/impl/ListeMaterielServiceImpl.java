package com.ime.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ime.api.model.ListeMateriel;
import com.ime.api.model.Materiel;
import com.ime.api.model.Tache;
import com.ime.api.repository.ListeMaterielRepository;
import com.ime.api.repository.MaterielRepository;
import com.ime.api.repository.TacheRepository;
import com.ime.api.service.ListeMaterielService;
import com.ime.api.service.dto.ListeMaterielDTO;
import com.ime.api.service.mapper.ListeMaterielMapper;

@Service
public class ListeMaterielServiceImpl implements ListeMaterielService {
    private final ListeMaterielRepository listeMaterielRepository;
    private final ListeMaterielMapper listeMaterielMapper;
    private final MaterielRepository materielRepository;
    private final TacheRepository tacheRepository;

    public ListeMaterielServiceImpl(
        ListeMaterielRepository listeMaterielRepository,
        ListeMaterielMapper listeMaterielMapper,
        MaterielRepository materielRepository,
        TacheRepository tacheRepository
    ) {
        this.listeMaterielRepository = listeMaterielRepository;
        this.listeMaterielMapper = listeMaterielMapper;
        this.materielRepository = materielRepository;
        this.tacheRepository = tacheRepository;
    }

    @Override
    public ListeMaterielDTO creer(ListeMaterielDTO listeMaterielDTO) {
        ListeMateriel listeMateriel = listeMaterielMapper.toEntity(listeMaterielDTO);

        // Récupérer le matériel complet
        if (listeMaterielDTO.getMateriel() != null && listeMaterielDTO.getMateriel().getId() != null) {
            Materiel materiel = materielRepository.findById(listeMaterielDTO.getMateriel().getId())
                .orElseThrow(() -> new RuntimeException("Matériel introuvable"));
            listeMateriel.setMateriel(materiel);
        } else {
            listeMateriel.setMateriel(null);
        }

        // Récupérer la tâche complète
        if (listeMaterielDTO.getTache() != null && listeMaterielDTO.getTache().getId() != null) {
            Tache tache = tacheRepository.findById(listeMaterielDTO.getTache().getId())
                .orElseThrow(() -> new RuntimeException("Tâche introuvable"));
            listeMateriel.setTache(tache);
        } else {
            listeMateriel.setTache(null);
        }

        ListeMateriel saved = listeMaterielRepository.save(listeMateriel);
        return listeMaterielMapper.toDto(saved);
    }

    @Override
    public List<ListeMaterielDTO> lire() {
        return listeMaterielRepository.findAll()
                .stream()
                .map(listeMaterielMapper::toDto)
                .toList();
    }

    @Override
    public ListeMaterielDTO modifier(Long id, ListeMaterielDTO listeMaterielDTO) {
        ListeMateriel listeMateriel = listeMaterielRepository.findById(id)
                .map(lm -> {
                    lm.setQuantite(listeMaterielDTO.getQuantite());
                    // Mettre à jour le matériel
                    if (listeMaterielDTO.getMateriel() != null && listeMaterielDTO.getMateriel().getId() != null) {
                        Materiel materiel = materielRepository.findById(listeMaterielDTO.getMateriel().getId())
                            .orElseThrow(() -> new RuntimeException("Matériel introuvable"));
                        lm.setMateriel(materiel);
                    } else {
                        lm.setMateriel(null);
                    }
                    // Mettre à jour la tâche
                    if (listeMaterielDTO.getTache() != null && listeMaterielDTO.getTache().getId() != null) {
                        Tache tache = tacheRepository.findById(listeMaterielDTO.getTache().getId())
                            .orElseThrow(() -> new RuntimeException("Tâche introuvable"));
                        lm.setTache(tache);
                    } else {
                        lm.setTache(null);
                    }
                    return listeMaterielRepository.save(lm);
                })
                .orElseThrow(() -> new RuntimeException("Association matériel-tâche introuvable !"));
        return listeMaterielMapper.toDto(listeMateriel);
    }

    @Override
    public String supprimer(Long id) {
        listeMaterielRepository.deleteById(id);
        return "Association matériel-tâche supprimée !";
    }

    @Override
    public ListeMaterielDTO lireUn(Long id) {
        ListeMateriel listeMateriel = listeMaterielRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Association matériel-tâche introuvable avec l'ID : " + id));
        return listeMaterielMapper.toDto(listeMateriel);
    }
}
