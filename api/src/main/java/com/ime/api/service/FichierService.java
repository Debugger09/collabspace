package com.ime.api.service;

import java.util.List;
import com.ime.api.service.dto.FichierDTO;

public interface FichierService {
    FichierDTO creer(FichierDTO fichierDTO);

    List<FichierDTO> lire();

    FichierDTO modifier(Long id, FichierDTO fichierDTO);

    String supprimer(Long id);

    FichierDTO lireUn(Long id);
}