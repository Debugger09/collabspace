package com.ime.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ime.api.model.ListeMembre;
import com.ime.api.model.User;
import com.ime.api.model.Tache;
import com.ime.api.repository.ListeMembreRepository;
import com.ime.api.repository.UserRepository;
import com.ime.api.repository.TacheRepository;
import com.ime.api.service.ListeMembreService;
import com.ime.api.service.dto.ListeMembreDTO;
import com.ime.api.service.mapper.ListeMembreMapper;

@Service
public class ListeMembreServiceImpl implements ListeMembreService {
    private final ListeMembreRepository listeMembreRepository;
    private final ListeMembreMapper listeMembreMapper;
    private final UserRepository userRepository;
    private final TacheRepository tacheRepository;

    public ListeMembreServiceImpl(
        ListeMembreRepository listeMembreRepository,
        ListeMembreMapper listeMembreMapper,
        UserRepository userRepository,
        TacheRepository tacheRepository
    ) {
        this.listeMembreRepository = listeMembreRepository;
        this.listeMembreMapper = listeMembreMapper;
        this.userRepository = userRepository;
        this.tacheRepository = tacheRepository;
    }

    @Override
    public ListeMembreDTO creer(ListeMembreDTO listeMembreDTO) {
        ListeMembre listeMembre = listeMembreMapper.toEntity(listeMembreDTO);

        // Récupérer le membre complet
        if (listeMembreDTO.getMembre() != null && listeMembreDTO.getMembre().getId() != null) {
            User membre = userRepository.findById(listeMembreDTO.getMembre().getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
            listeMembre.setMembre(membre);
        } else {
            listeMembre.setMembre(null);
        }

        // Récupérer la tâche complète
        if (listeMembreDTO.getTache() != null && listeMembreDTO.getTache().getId() != null) {
            Tache tache = tacheRepository.findById(listeMembreDTO.getTache().getId())
                .orElseThrow(() -> new RuntimeException("Tâche introuvable"));
            listeMembre.setTache(tache);
        } else {
            listeMembre.setTache(null);
        }

        ListeMembre saved = listeMembreRepository.save(listeMembre);
        return listeMembreMapper.toDto(saved);
    }

    @Override
    public List<ListeMembreDTO> lire() {
        return listeMembreRepository.findAll()
                .stream()
                .map(listeMembreMapper::toDto)
                .toList();
    }

    @Override
    public ListeMembreDTO modifier(Long id, ListeMembreDTO listeMembreDTO) {
        ListeMembre listeMembre = listeMembreRepository.findById(id)
                .map(lm -> {
                    // Mettre à jour le membre
                    if (listeMembreDTO.getMembre() != null && listeMembreDTO.getMembre().getId() != null) {
                        User membre = userRepository.findById(listeMembreDTO.getMembre().getId())
                            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
                        lm.setMembre(membre);
                    } else {
                        lm.setMembre(null);
                    }
                    // Mettre à jour la tâche
                    if (listeMembreDTO.getTache() != null && listeMembreDTO.getTache().getId() != null) {
                        Tache tache = tacheRepository.findById(listeMembreDTO.getTache().getId())
                            .orElseThrow(() -> new RuntimeException("Tâche introuvable"));
                        lm.setTache(tache);
                    } else {
                        lm.setTache(null);
                    }
                    return listeMembreRepository.save(lm);
                })
                .orElseThrow(() -> new RuntimeException("Association membre-tâche introuvable !"));
        return listeMembreMapper.toDto(listeMembre);
    }

    @Override
    public String supprimer(Long id) {
        listeMembreRepository.deleteById(id);
        return "Association membre-tâche supprimée !";
    }

    @Override
    public ListeMembreDTO lireUn(Long id) {
        ListeMembre listeMembre = listeMembreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Association membre-tâche introuvable avec l'ID : " + id));
        return listeMembreMapper.toDto(listeMembre);
    }
}