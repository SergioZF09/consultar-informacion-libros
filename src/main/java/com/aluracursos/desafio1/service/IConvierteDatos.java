package com.aluracursos.desafio1.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);

}
