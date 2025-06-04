package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.DepenseService;
import com.ime.api.service.dto.DepenseDTO;

@RestController
@RequestMapping("/api/depense")
public class DepenseController {
    private final DepenseService depenseService;

    public DepenseController(DepenseService depenseService) {
        this.depenseService = depenseService;
    }

    @PostMapping("/create")
    public DepenseDTO create(@RequestBody DepenseDTO depenseDTO) {
        return depenseService.creer(depenseDTO);
    }

    @GetMapping("/read")
    public List<DepenseDTO> read() {
        return depenseService.lire();
    }

    @PutMapping("/{id}")
    public DepenseDTO update(@RequestBody DepenseDTO depenseDTO, @PathVariable Long id) {
        return depenseService.modifier(id, depenseDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return depenseService.supprimer(id);
    }

    @GetMapping("/{id}")
    public DepenseDTO readOne(@PathVariable Long id) {
        return depenseService.lireUn(id);
    }
}