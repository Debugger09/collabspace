package com.ime.api.service;

import java.util.List;

import com.ime.api.service.dto.UserDTO;

public interface UserService {
    UserDTO creer(UserDTO userDTO);

    List<UserDTO> lire();

    UserDTO modifier(Long id, UserDTO userDTO);

    String supprimer(Long id);

    UserDTO lireUn(Long id);

}