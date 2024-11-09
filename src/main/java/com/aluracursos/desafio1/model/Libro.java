package com.aluracursos.desafio1.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Libro {

    private Integer id;
    private String titulo;
    private List<DatosAutores> autores;
    private List<String> idiomas;
    private Double numeroDescargas;

    public Libro(DatosLibros libros) {
        this.id = libros.id();
        this.titulo = libros.titulo();
        this.autores = libros.autores();
        this.idiomas = libros.idiomas();

        try {

            this.numeroDescargas = libros.numeroDescargas();

        } catch (NumberFormatException e) {

            this.numeroDescargas = 0.0;

        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DatosAutores> getAutores() {
        return autores;
    }

    public void setAutores(List<DatosAutores> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas;
    }
}
