package com.ime.api.service;

import java.util.List;
import com.ime.api.service.dto.ListeMembreDTO;

public interface ListeMembreService {
    ListeMembreDTO creer(ListeMembreDTO listeMembreDTO);

    List<ListeMembreDTO> lire();

    ListeMembreDTO modifier(Long id, ListeMembreDTO listeMembreDTO);

    String supprimer(Long id);

    ListeMembreDTO lireUn(Long id);
}