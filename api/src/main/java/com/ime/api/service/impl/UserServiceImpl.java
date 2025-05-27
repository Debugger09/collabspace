package com.ime.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ime.api.model.User;
import com.ime.api.repository.UserRepository;
import com.ime.api.service.UserService;
import com.ime.api.service.dto.UserDTO;
import com.ime.api.service.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO creer(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        // On encode le mot de passe AVANT de sauvegarder
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> lire() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO modifier(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .map(p -> {
                    p.setEmail(userDTO.getEmail());
                    p.setLastName(userDTO.getLastName());
                    p.setFirstName(userDTO.getFirstName());
                    if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                        p.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    }
                    p.setPhone(userDTO.getPhone());
                    p.setJob(userDTO.getJob());
                    p.setRole(userDTO.getRole());
                    p.setStatus(userDTO.isStatus());
                    return userRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Utilisateur introuvable !"));

        return userMapper.toDto(user);
    }

    @Override
    public String supprimer(Long id) {
        userRepository.deleteById(id);
        return "Utilisateur supprimé !";
    }

    @Override
    public UserDTO lireUn(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'ID : " + id));
        return userMapper.toDto(user); // Utiliser userMapper
    }
}