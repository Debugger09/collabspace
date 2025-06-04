package com.ime.api.service;

import java.util.List;


import com.ime.api.service.dto.ProjetDTO;

public interface ProjetService {
    ProjetDTO creer(ProjetDTO projetDTO);

    List<ProjetDTO> lire();
    
    ProjetDTO modifier(Long id, ProjetDTO projetDTO);
    
    String supprimer(Long id);
   
    ProjetDTO lireUn(Long id);

}
