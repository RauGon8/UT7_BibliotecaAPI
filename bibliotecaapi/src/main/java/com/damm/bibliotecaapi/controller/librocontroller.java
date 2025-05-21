package com.damm.bibliotecaapi.controller;

import com.damm.bibliotecaapi.model.libro;
import com.damm.bibliotecaapi.model.autor;
import com.damm.bibliotecaapi.service.LibroService;
import com.damm.bibliotecaapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/libros")
public class librocontroller {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorRepository autorRepository;

    // GET /api/v1/libros
    @GetMapping
    public List<libro> getAllLibros(){
        return libroService.findAll();
    } 

    // GET /api/v1/libros/{id}
    @GetMapping("id")
    public ResponseEntity<libro>getLibroById(@PathVariable Long id){
        Optional<libro> libro =libroService.findById(id);
        return libro.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }

    // POST /api/v1/libros
    @PostMapping
    public ResponseEntity<libro> createLibro(@RequestBody libro libro){
        if (libro.getAutor() != null&& libro.getAutor().getId() != null) {
            Optional<autor> autorOpt =autorRepository.findById(libro.getAutor().getId());
            if (autorOpt.isPresent()) {
                libro.setAutor(autorOpt.get());
                libro saved =libroService.save(libro);
            return ResponseEntity.ok(saved);
            } else{
                return ResponseEntity.badRequest().build();
            }
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/v1/libros/{id}
    @PutMapping("id")
    public ResponseEntity<libro> updateLibro(@PathVariable Long id, @RequestBody libro libroDetails) {
        Optional<libro> libroOpt =libroService.findById(id);
        if (libroOpt.isPresent()) {
            libro libroToUpdate =libroOpt.get();
            libroToUpdate.setTitulo(libroDetails.getTitulo());
            libroToUpdate.setIsbn(libroDetails.getIsbn());
            libroToUpdate.setAñoPublicacion(libroDetails.getAñoPublicacion());
            if (libroDetails.getAutor()!= null &&libroDetails.getAutor().getId() != null) {
                Optional<autor> autorOpt =autorRepository.findById(libroDetails.getAutor().getId());
                autorOpt.ifPresent(libroToUpdate::setAutor);
            }
            libro updated =libroService.save(libroToUpdate);
        return ResponseEntity.ok(updated);
        } else{
        return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/v1/libros/{id}
    @DeleteMapping("id")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id){
        if (libroService.findById(id).isPresent()){
            libroService.deleteById(id);
        return ResponseEntity.noContent().build();
        } else {
        return ResponseEntity.notFound().build();
        }
    }

    // GET /api/v1/libros/buscar
    @GetMapping("/buscar")
    public List<libro>buscarLibros(
            @RequestParam(required = false)String titulo,
            @RequestParam(required =false,name ="año") Integer año,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false)String order
    ) {
        return libroService.buscar(titulo,año,sortBy,order);
    }
}