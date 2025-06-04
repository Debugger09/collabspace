package com.ime.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ime.api.service.MessageService;
import com.ime.api.service.dto.MessageDTO;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/create")
    public MessageDTO create(@RequestBody MessageDTO messageDTO) {
        return messageService.creer(messageDTO);
    }

    @GetMapping("/read")
    public List<MessageDTO> read() {
        return messageService.lire();
    }

    @PutMapping("/{id}")
    public MessageDTO update(@RequestBody MessageDTO messageDTO, @PathVariable Long id) {
        return messageService.modifier(id, messageDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return messageService.supprimer(id);
    }

    @GetMapping("/{id}")
    public MessageDTO readOne(@PathVariable Long id) {
        return messageService.lireUn(id);
    }
}