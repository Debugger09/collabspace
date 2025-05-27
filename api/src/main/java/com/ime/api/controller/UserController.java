package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.UserService;
import com.ime.api.service.dto.UserDTO;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService; // Utilisation de final pour l'injection de constructeur

    public UserController(UserService userService) { // Injection de constructeur
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        return userService.creer(userDTO);

    }

    @GetMapping("/read")
    public List<UserDTO> read() {
        return userService.lire();
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.modifier(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return userService.supprimer(id);
    }

    @GetMapping("/{id}")
    public UserDTO readOne(@PathVariable Long id) {
        return userService.lireUn(id);
    }

}

