package com.ime.api.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ime.api.model.Projet;
import com.ime.api.model.User;
import com.ime.api.repository.ProjetRepository;
import com.ime.api.repository.UserRepository;
import com.ime.api.service.ProjetService;
import com.ime.api.service.dto.ProjetDTO;
import com.ime.api.service.mapper.ProjetMapper;

@Service
public class ProjetServiceImpl implements ProjetService {
    private final ProjetRepository projetRepository;
    private final ProjetMapper projetMapper;
    private final UserRepository userRepository;

    public ProjetServiceImpl(ProjetRepository projetRepository, ProjetMapper projetMapper, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.projetRepository = projetRepository;
        this.projetMapper = projetMapper;
    }

    @Override
    public ProjetDTO creer(ProjetDTO projetDTO) {
        Projet projet = projetMapper.toEntity(projetDTO);

        // Récupérer le chef de projet complet
        if (projetDTO.getChefProjet() != null && projetDTO.getChefProjet().getId() != null) {
             User chef = userRepository.findById(projetDTO.getChefProjet().getId())
                .orElseThrow(() -> new RuntimeException("Chef de projet introuvable"));
        projet.setChefProjet(chef);
        } else {
        projet.setChefProjet(null);
        }

        Projet saved = projetRepository.save(projet);
        return projetMapper.toDto(saved);
   }

    @Override
    public List<ProjetDTO> lire() {
        return projetRepository.findAll()
                .stream()
                .map(projetMapper::toDto)
                .toList();
    }

    @Override
    public ProjetDTO modifier(Long id, ProjetDTO projetDTO) {
        Projet projet = projetRepository.findById(id)
                .map(p -> {
                    p.setName(projetDTO.getName());
                    p.setDateDebut(projetDTO.getDateDebut());
                    p.setDateFin(projetDTO.getDateFin());
                    p.setDescription(projetDTO.getDescription());
                    p.setStatus(projetDTO.getStatus());
                    p.setBudget(projetDTO.getBudget());
                    // Charger le chef de projet complet
                    if (projetDTO.getChefProjet() != null && projetDTO.getChefProjet().getId() != null) {
                        User chef = userRepository.findById(projetDTO.getChefProjet().getId())
                            .orElseThrow(() -> new RuntimeException("Chef de projet introuvable"));
                        p.setChefProjet(chef);
                    } else {
                        p.setChefProjet(null);
                    }
                    return projetRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Projet introuvable !"));
        return projetMapper.toDto(projet);
    }

    @Override
    public String supprimer(Long id) {
        projetRepository.deleteById(id);
        return "Projet supprimé !";
    }

    @Override
    public ProjetDTO lireUn(Long id) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet introuvable avec l'ID : " + id));
        return projetMapper.toDto(projet);
    }

}
