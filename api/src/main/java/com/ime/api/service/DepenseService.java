package com.ime.api.service;

import java.util.List;
import com.ime.api.service.dto.DepenseDTO;

public interface DepenseService {
    DepenseDTO creer(DepenseDTO depenseDTO);

    List<DepenseDTO> lire();

    DepenseDTO modifier(Long id, DepenseDTO depenseDTO);

    String supprimer(Long id);

    DepenseDTO lireUn(Long id);
}