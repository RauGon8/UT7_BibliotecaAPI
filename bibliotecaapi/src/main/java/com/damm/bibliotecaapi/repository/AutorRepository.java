package com.damm.bibliotecaapi.repository;

import com.damm.bibliotecaapi.model.autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<autor, Long> {
}