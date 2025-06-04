package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.ListeMembreService;
import com.ime.api.service.dto.ListeMembreDTO;

@RestController
@RequestMapping("/api/listemembre")
public class ListeMembreController {
    private final ListeMembreService listeMembreService;

    public ListeMembreController(ListeMembreService listeMembreService) {
        this.listeMembreService = listeMembreService;
    }

    @PostMapping("/create")
    public ListeMembreDTO create(@RequestBody ListeMembreDTO listeMembreDTO) {
        return listeMembreService.creer(listeMembreDTO);
    }

    @GetMapping("/read")
    public List<ListeMembreDTO> read() {
        return listeMembreService.lire();
    }

    @PutMapping("/{id}")
    public ListeMembreDTO update(@RequestBody ListeMembreDTO listeMembreDTO, @PathVariable Long id) {
        return listeMembreService.modifier(id, listeMembreDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return listeMembreService.supprimer(id);
    }

    @GetMapping("/{id}")
    public ListeMembreDTO readOne(@PathVariable Long id) {
        return listeMembreService.lireUn(id);
    }
}