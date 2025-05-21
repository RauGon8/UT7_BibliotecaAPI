package com.damm.bibliotecaapi.model;

import jakarta.persistence.*;

@Entity
public class libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;
    private int añoPublicacion;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private autor autor;

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo){ this.titulo = titulo; }

    public String getIsbn(){ return isbn; }
    public void setIsbn(String isbn){ this.isbn = isbn; }

    public int getAñoPublicacion(){ return añoPublicacion; }
    public void setAñoPublicacion(int añoPublicacion) { this.añoPublicacion = añoPublicacion; }

    public autor getAutor() { return autor; }
    public void setAutor(autor autor) { this.autor = autor; }
     
    public libro(){

        
    }

    public libro(String titulo, String isbn, int añoPublicacion, autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.añoPublicacion = añoPublicacion;
        this.autor = autor;
    }
}