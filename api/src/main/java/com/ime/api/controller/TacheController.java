package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.TacheService;
import com.ime.api.service.dto.TacheDTO;

@RestController
@RequestMapping("/api/tache")
public class TacheController {
    private final TacheService tacheService;

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    @PostMapping("/create")
    public TacheDTO create(@RequestBody TacheDTO tacheDTO) {
        return tacheService.creer(tacheDTO);
    }

    @GetMapping("/read")
    public List<TacheDTO> read() {
        return tacheService.lire();
    }

    @PutMapping("/{id}")
    public TacheDTO update(@RequestBody TacheDTO tacheDTO, @PathVariable Long id) {
        return tacheService.modifier(id, tacheDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return tacheService.supprimer(id);
    }

    @GetMapping("/{id}")
    public TacheDTO readOne(@PathVariable Long id) {
        return tacheService.lireUn(id);
    }
}
