package com.ime.api.service;

import java.util.List;
import com.ime.api.service.dto.ListeMaterielDTO;

public interface ListeMaterielService {
    ListeMaterielDTO creer(ListeMaterielDTO listeMaterielDTO);

    List<ListeMaterielDTO> lire();

    ListeMaterielDTO modifier(Long id, ListeMaterielDTO listeMaterielDTO);

    String supprimer(Long id);

    ListeMaterielDTO lireUn(Long id);
}
