package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.MaterielService;
import com.ime.api.service.dto.MaterielDTO;

@RestController
@RequestMapping("/api/materiel")
public class MaterielController {
    private final MaterielService materielService;

    public MaterielController(MaterielService materielService) {
        this.materielService = materielService;
    }

    @PostMapping("/create")
    public MaterielDTO create(@RequestBody MaterielDTO materielDTO) {
        return materielService.creer(materielDTO);
    }

    @GetMapping("/read")
    public List<MaterielDTO> read() {
        return materielService.lire();
    }

    @PutMapping("/{id}")
    public MaterielDTO update(@RequestBody MaterielDTO materielDTO, @PathVariable Long id) {
        return materielService.modifier(id, materielDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return materielService.supprimer(id);
    }

    @GetMapping("/{id}")
    public MaterielDTO readOne(@PathVariable Long id) {
        return materielService.lireUn(id);
    }
}
