package com.aluracursos.desafio1.principal;

import com.aluracursos.desafio1.model.DatosLibros;
import com.aluracursos.desafio1.model.DatosResultados;
import com.aluracursos.desafio1.model.Libro;
import com.aluracursos.desafio1.service.ConsumoAPI;
import com.aluracursos.desafio1.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu(){

        //Muestra la info de la api
        var json = consumoApi.obtenerDatos(URL_BASE);
        //System.out.println(json);

        //Convierte la info de la api a objeto de DatosResultados con los otros Datos
        var datos = conversor.obtenerDatos(json, DatosResultados.class);
        System.out.println(datos);

        //Top 10 libros más descargados
        System.out.println("\nTop 10 libros más descargados");

        //Ordena los libros más descargados, lo limita a 10 libros, recorre cada titulo
        //de los libros de la lista e imprime dichos titulos
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDescargas))
                .limit(10)
                .map(t -> t.titulo())
                .forEach(System.out::println);

        //Convirtiendo los datos a una lista del tipo Libro
        List<Libro> libros = datos.resultados().stream()
                .map(l -> new Libro(l))
                .collect(Collectors.toList());

        //Busqueda del libro usando una parte del titulo
        System.out.println("\nIngresa el nombre del libro que deseas buscar");
        var titulo = teclado.nextLine();

        Optional<Libro> libroEncontrado = libros.stream()
                .filter(l -> l.getTitulo().toUpperCase().contains(titulo.toUpperCase()))
                .findFirst();

        if (libroEncontrado.isPresent()) {

            System.out.println("\nLibro encontrado");

            System.out.println("Los datos son: " + libroEncontrado.get());

        } else {

            System.out.println("Libro no encontrado");

        }

        //Mostrar estadísticas de los libros
        DoubleSummaryStatistics est = libros.stream()
                .filter(l -> l.getNumeroDescargas() > 0.0)
                .collect(Collectors.summarizingDouble(Libro::getNumeroDescargas));

        System.out.println("Media de descargas: " + est.getAverage());
        System.out.println("Número máximo de descargas: " + est.getMax());
        System.out.println("Número mínimo de descargas: " + est.getMin());
        System.out.println("Número de registros evaluados: " + est.getCount());

    }

}
