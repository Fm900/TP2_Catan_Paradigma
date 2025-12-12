package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Construccion.Ciudad;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VerificarLaProducciónCorrecta1RecursoPorPoblado2RecursosPorCiudadAdyacentesAlNúmeroLanzado {
    @Test
    public void test01ElPobladoGeneraUnSoloRecurso() {
        List<Recurso> listaRecursos = (List.of(new Madera(), new Ladrillo(), new Grano(), new Lana()));
        List<Recurso> listaConMadera = (List.of(new Madera()));
        Vertice vertice = new Vertice();
        Recurso recurso = new Madera();
        Terreno terrenoDestino = new Terreno(recurso, 10, (new Normal()));
        Jugador jugador = new Jugador(new MazoDeRecursos(listaRecursos), new Mano());
        Poblado poblado = new Poblado(1, 1, jugador);

        vertice.construirPoblado(jugador);

        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice));

        terrenoDestino.producirSiCorresponde(10);


        assertDoesNotThrow(() -> jugador.consumirRecursos(listaConMadera), "No tienes suficiente");
        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(listaConMadera), "No tienes suficiente");
    }

    @Test
    public void test02LaCiudadGeneraDosRecursos() {
        List<Recurso> listaConMadera = (List.of(new Madera()));
        Vertice vertice = new Vertice();
        Recurso recurso = new Madera();
        Terreno terrenoDestino = new Terreno(recurso, 10, (new Normal()));
        List<Recurso> listaRecursos = (List.of(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral(), new Madera(), new Ladrillo(), new Grano(), new Lana()));
        Jugador jugador = new Jugador(new MazoDeRecursos(listaRecursos), new Mano());
        Poblado poblado = new Poblado(1, 1, jugador);
        Ciudad ciudad = new Ciudad(2, 2, jugador);

        vertice.construirPoblado(jugador);

        vertice.mejorarPobladoACiudad(jugador);

        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice));

        terrenoDestino.producirSiCorresponde(10);


        assertDoesNotThrow(() -> jugador.consumirRecursos(listaConMadera), "No tienes suficiente");
        assertDoesNotThrow(() -> jugador.consumirRecursos(listaConMadera), "No tienes suficiente");
        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(listaConMadera), "No tienes suficiente");
    }
}
