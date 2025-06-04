package com.ime.api.service;

import java.util.List;
import com.ime.api.service.dto.MaterielDTO;

public interface MaterielService {
    MaterielDTO creer(MaterielDTO materielDTO);

    List<MaterielDTO> lire();

    MaterielDTO modifier(Long id, MaterielDTO materielDTO);

    String supprimer(Long id);

    MaterielDTO lireUn(Long id);
}