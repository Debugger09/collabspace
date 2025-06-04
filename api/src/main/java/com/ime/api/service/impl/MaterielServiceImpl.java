package com.ime.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ime.api.model.Materiel;
import com.ime.api.repository.MaterielRepository;
import com.ime.api.service.MaterielService;
import com.ime.api.service.dto.MaterielDTO;
import com.ime.api.service.mapper.MaterielMapper;

@Service
public class MaterielServiceImpl implements MaterielService {
    private final MaterielRepository materielRepository;
    private final MaterielMapper materielMapper;

    public MaterielServiceImpl(MaterielRepository materielRepository, MaterielMapper materielMapper) {
        this.materielRepository = materielRepository;
        this.materielMapper = materielMapper;
    }

    @Override
    public MaterielDTO creer(MaterielDTO materielDTO) {
        Materiel materiel = materielMapper.toEntity(materielDTO);
        Materiel saved = materielRepository.save(materiel);
        return materielMapper.toDto(saved);
    }

    @Override
    public List<MaterielDTO> lire() {
        return materielRepository.findAll()
                .stream()
                .map(materielMapper::toDto)
                .toList();
    }

    @Override
    public MaterielDTO modifier(Long id, MaterielDTO materielDTO) {
        Materiel materiel = materielRepository.findById(id)
                .map(m -> {
                    m.setLibelle(materielDTO.getLibelle());
                    m.setReutilisable(materielDTO.isReutilisable());
                    m.setActif(materielDTO.isActif());
                    m.setQuantite(materielDTO.getQuantite());
                    return materielRepository.save(m);
                })
                .orElseThrow(() -> new RuntimeException("Matériel introuvable !"));
        return materielMapper.toDto(materiel);
    }

    @Override
    public String supprimer(Long id) {
        materielRepository.deleteById(id);
        return "Matériel supprimé !";
    }

    @Override
    public MaterielDTO lireUn(Long id) {
        Materiel materiel = materielRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matériel introuvable avec l'ID : " + id));
        return materielMapper.toDto(materiel);
    }
}
