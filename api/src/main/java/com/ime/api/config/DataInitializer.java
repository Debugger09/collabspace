package com.ime.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.ime.api.service.UserService;
import com.ime.api.service.dto.UserDTO;
import com.ime.api.model.enums.ROLE; // Ajoutez cet import si nécessaire

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        // Vérifie si l'utilisateur existe déjà
        if (userService.lire().isEmpty()) {
            UserDTO user = new UserDTO();
            user.setFirstName("Admin");
            user.setLastName("Default");
            user.setEmail("admin@collabspace.com");
            user.setPassword("admin123"); // à hasher selon ta logique
            user.setRole(ROLE.ADMIN);
            user.setStatus(true);
            userService.creer(user);
            System.out.println("Utilisateur admin par défaut créé !");
        }
    }
}