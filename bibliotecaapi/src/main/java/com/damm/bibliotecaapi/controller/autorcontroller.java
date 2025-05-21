package com.damm.bibliotecaapi.controller;

import com.damm.bibliotecaapi.model.autor;
import com.damm.bibliotecaapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
public class autorcontroller {
    private final AutorService autorService;

    public autorcontroller(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<autor> getAllAutores() {
        return autorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<autor> getAutorById(@PathVariable Long id) {
        return autorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public autor createAutor(@RequestBody autor autor) {
        return autorService.save(autor);
    }
}