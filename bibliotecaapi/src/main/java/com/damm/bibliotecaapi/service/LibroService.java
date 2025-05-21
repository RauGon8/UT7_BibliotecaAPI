package com.damm.bibliotecaapi.service;

import com.damm.bibliotecaapi.model.libro;
import com.damm.bibliotecaapi.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<libro> findAll(){
        return libroRepository.findAll();
    }

    public Optional<libro> findById(Long id){
        return libroRepository.findById(id);
    }

    public libro save(libro libro){
        return libroRepository.save(libro);
    }

    public void deleteById(Long id){
        libroRepository.deleteById(id);
    }

    public List<libro> buscar(String titulo, Integer año, String sortBy, String order){
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isEmpty()){
            sort = "desc".equalsIgnoreCase(order) ? Sort.by(sortBy).descending() :Sort.by(sortBy).ascending();
        }

        if (titulo != null && año != null){
            return libroRepository.findAll((root, query, cb) -> cb.and(
                    cb.like(cb.lower(root.get("titulo")), "%"+ titulo.toLowerCase() +"%"),
                    cb.equal(root.get("añoPublicacion"), año)
            ), sort);
        } else if (titulo != null){
            return libroRepository.findAll((root, query, cb) ->
                    cb.like(cb.lower(root.get("titulo")), "%" +titulo.toLowerCase()+"%"),sort);
        } else if (año != null){
            return libroRepository.findAll((root, query, cb) ->
                    cb.equal(root.get("añoPublicacion"),año),sort);
        } else {
            return libroRepository.findAll(sort);
        }
    }
}