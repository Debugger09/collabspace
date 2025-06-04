package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.ListeMaterielService;
import com.ime.api.service.dto.ListeMaterielDTO;

@RestController
@RequestMapping("/api/listemateriel")
public class ListeMaterielController {
    private final ListeMaterielService listeMaterielService;

    public ListeMaterielController(ListeMaterielService listeMaterielService) {
        this.listeMaterielService = listeMaterielService;
    }

    @PostMapping("/create")
    public ListeMaterielDTO create(@RequestBody ListeMaterielDTO listeMaterielDTO) {
        return listeMaterielService.creer(listeMaterielDTO);
    }

    @GetMapping("/read")
    public List<ListeMaterielDTO> read() {
        return listeMaterielService.lire();
    }

    @PutMapping("/{id}")
    public ListeMaterielDTO update(@RequestBody ListeMaterielDTO listeMaterielDTO, @PathVariable Long id) {
        return listeMaterielService.modifier(id, listeMaterielDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return listeMaterielService.supprimer(id);
    }

    @GetMapping("/{id}")
    public ListeMaterielDTO readOne(@PathVariable Long id) {
        return listeMaterielService.lireUn(id);
    }
}
