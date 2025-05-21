package com.damm.bibliotecaapi.repository;

import com.damm.bibliotecaapi.model.libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LibroRepository extends JpaRepository<libro, Long>, JpaSpecificationExecutor<libro> {
}