package com.damm.bibliotecaapi.service;

import com.damm.bibliotecaapi.model.autor;
import com.damm.bibliotecaapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<autor> findAll() {
        return autorRepository.findAll();
    }

    public Optional<autor> findById(Long id) {
        return autorRepository.findById(id);
    }

    public autor save(autor autor) {
        return autorRepository.save(autor);
    }
}