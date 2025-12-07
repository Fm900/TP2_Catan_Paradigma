package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Construccion.Ciudad;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Exception.NoAlcanzanLosRecursos;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Turnos.Fase.Dados;
import edu.fiuba.algo3.modelo.Turnos.Primer;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VerificarLaProducciónCorrecta1RecursoPorPoblado2RecursosPorCiudadAdyacentesAlNúmeroLanzado {


    List<Recurso> listaRecursos;
    List<Recurso> listaConMadera;
    Vertice vertice;
    Recurso recurso;
    Terreno terrenoDestino;
    Jugador jugador;
    Jugador jugador2;
    Poblado poblado;

    @BeforeEach
    public void setup(){
        listaRecursos = (List.of(new Madera(), new Ladrillo(), new Grano(), new Lana()));
        listaConMadera = (List.of(new Madera()));
        vertice = new Vertice(1,0,0);
        recurso = new Madera();
        terrenoDestino = new Terreno(recurso, 10, (new Normal()));
        jugador = new Jugador(new MazoDeRecursos(listaRecursos), new Mano(), "Alex", Color.RED);
        poblado = new Poblado(1, 1, jugador);

        List<Recurso> listaRecursos = (List.of(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral(), new Madera(), new Ladrillo(), new Grano(), new Lana()));
        jugador2 = new Jugador(new MazoDeRecursos(listaRecursos), new Mano(),"Alex", Color.BLACK);
        List<Carta> cartas = new ArrayList<>();
        Juego.crearInstancia(List.of(jugador ,jugador2), new Tablero(new ArrayList<Terreno>(), new ArrayList<Vertice>(), new ArrayList<Arista>()), Banca.crearBanca(List.of(new Madera()), cartas));

    }



    @Test
    public void test01ElPobladoGeneraUnSoloRecurso() {

        vertice.construirPobladoInicial(jugador);

        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice));

        terrenoDestino.producirSiCorresponde(10);


        assertDoesNotThrow(() -> jugador.consumirRecursos(listaConMadera), "No tienes suficiente");
        assertThrows(NoAlcanzanLosRecursos.class, () -> jugador.consumirRecursos(listaConMadera), "No tienes suficiente");
    }

    @Test
    public void test02LaCiudadGeneraDosRecursos() {
        List<Recurso> listaConMadera = (List.of(new Madera()));
        Vertice vertice = new Vertice(0, 1,1);
        Recurso recurso = new Madera();
        Terreno terrenoDestino = new Terreno(recurso, 10, (new Normal()));
        Poblado poblado = new Poblado(1, 1, jugador2);
        Ciudad ciudad = new Ciudad(2, 2, jugador2);

        vertice.construirPobladoInicial(jugador2);

        vertice.mejorarPobladoACiudad(jugador2);

        terrenoDestino.asignarVerticesAdyacentes(List.of(vertice));

        terrenoDestino.producirSiCorresponde(10);


        assertDoesNotThrow(() -> jugador2.consumirRecursos(listaConMadera), "No tienes suficiente");
        assertDoesNotThrow(() -> jugador2.consumirRecursos(listaConMadera), "No tienes suficiente");
        assertThrows(RuntimeException.class, () -> jugador2.consumirRecursos(listaConMadera), "No tienes suficiente");
    }
}
