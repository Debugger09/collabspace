package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.FichierService;
import com.ime.api.service.dto.FichierDTO;

@RestController
@RequestMapping("/api/fichier")
public class FichierController {
    private final FichierService fichierService;

    public FichierController(FichierService fichierService) {
        this.fichierService = fichierService;
    }

    @PostMapping("/create")
    public FichierDTO create(@RequestBody FichierDTO fichierDTO) {
        return fichierService.creer(fichierDTO);
    }

    @GetMapping("/read")
    public List<FichierDTO> read() {
        return fichierService.lire();
    }

    @PutMapping("/{id}")
    public FichierDTO update(@RequestBody FichierDTO fichierDTO, @PathVariable Long id) {
        return fichierService.modifier(id, fichierDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return fichierService.supprimer(id);
    }

    @GetMapping("/{id}")
    public FichierDTO readOne(@PathVariable Long id) {
        return fichierService.lireUn(id);
    }
}
