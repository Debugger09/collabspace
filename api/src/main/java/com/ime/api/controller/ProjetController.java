package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.ProjetService;
import com.ime.api.service.dto.ProjetDTO;

@RestController
@RequestMapping("/api/projet")
public class ProjetController {
    private final ProjetService projetService;
    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @PostMapping("/create")
    public ProjetDTO create(@RequestBody ProjetDTO projetDTO) {
        return projetService.creer(projetDTO);
    }

    @GetMapping("/read")
    public List<ProjetDTO> read() { 
        return projetService.lire();
    }

    @PutMapping("/{id}")
    public ProjetDTO update(@RequestBody ProjetDTO projetDTO, @PathVariable Long id) {
        return projetService.modifier(id, projetDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return projetService.supprimer(id);
    }

    @GetMapping("/{id}")
    public ProjetDTO readOne(@PathVariable Long id) {
        return projetService.lireUn(id);
    }

}
