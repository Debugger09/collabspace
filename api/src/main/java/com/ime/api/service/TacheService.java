package com.ime.api.service;

import java.util.List;
import com.ime.api.service.dto.TacheDTO;

public interface TacheService {
    TacheDTO creer(TacheDTO tacheDTO);

    List<TacheDTO> lire();

    TacheDTO modifier(Long id, TacheDTO tacheDTO);

    String supprimer(Long id);

    TacheDTO lireUn(Long id);
}
