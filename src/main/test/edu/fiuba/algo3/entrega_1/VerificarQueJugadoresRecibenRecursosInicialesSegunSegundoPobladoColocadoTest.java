package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.controllers.ManejoTurnos;
import edu.fiuba.algo3.modelo.Turnos.*;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Constructores.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Tablero.Arista.Arista;
import edu.fiuba.algo3.modelo.Tablero.Arista.Vacia;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Normal;
import edu.fiuba.algo3.modelo.Tablero.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import edu.fiuba.algo3.modelo.Turnos.Fase.*;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class VerificarQueJugadoresRecibenRecursosInicialesSegunSegundoPobladoColocadoTest {

    class GeneradorDeTerrenosControlado extends GeneradorDeTerrenos {
        private final List<Terreno> terrenos;
        public GeneradorDeTerrenosControlado(List<Terreno> terrenos) { this.terrenos = terrenos; }
        @Override
        public List<Terreno> generar() { return terrenos; }
    }

    private Jugador nuevoJugadorConRecursosIniciales() {
        // recursos para dos poblados y dos carreteras
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<>(List.of(new Madera(), new Madera(), new Madera(), new Madera(), new Ladrillo(), new Ladrillo(), new Ladrillo(), new Ladrillo(), new Lana(), new Lana(), new Grano(), new Grano())));
        Mano mano = new Mano();
        return new Jugador(gestor, mano,"Roberto", Color.RED);
    }

    @Test
    void Test01JugadorRecibeRecursosInicialesSegunTerrenosAdyacentes(){
        Juego.reset();
        Jugador jugador = nuevoJugadorConRecursosIniciales();
        Vertice verticePrimerTurno = new Vertice(1, 1.0, 1.0);
        Vertice verticeSegundoTurno = new Vertice(2, 2.0, 2.0);


        Terreno terrenoConMineral = new Terreno(new Mineral(), 5, new Normal());
        terrenoConMineral.asignarVerticesAdyacentes(List.of(verticeSegundoTurno));

        List<Carta> cartas = new ArrayList<>();
        Banca banca = Banca.crearBanca(new ArrayList<>(), cartas);

        List<Recurso> precio = List.of(new Mineral());

        Arista aristaPrimerTurno = new Arista(verticePrimerTurno, new Vertice(0, 1.0, 1.0), new Vacia());
        Arista aristaSegundoTurno = new Arista(verticeSegundoTurno, new Vertice(0, 1.0, 1.0), new Vacia());

        Tablero tablero = new Tablero(new ArrayList<Terreno>(List.of(terrenoConMineral)), new ArrayList<Vertice>(List.of(verticePrimerTurno, verticeSegundoTurno)), new ArrayList<Arista>(List.of(aristaPrimerTurno, aristaSegundoTurno)));

        Juego.crearInstancia(List.of(jugador), tablero, banca);

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        ManejoTurnos manejoTurnos = new ManejoTurnos(jugadores);

        // 1) Primer turno: construye poblado inicial + carretera, pero no recibe recursos iniciales
        Primer primerTurno = new Primer();
        primerTurno.construir(manejoTurnos, verticePrimerTurno, aristaPrimerTurno);

        assertThrows(RuntimeException.class, () -> jugador.consumirRecursos(precio), "Antes del segundo poblado no deberia poder pagar un mineral");

        // 2) Segundo turno: construye poblado inicial + carretera en vertice adyacente al terreno de mineral
        Segundo segundoTurno = new Segundo();
        segundoTurno.construir(manejoTurnos, verticeSegundoTurno, aristaSegundoTurno);

        assertDoesNotThrow(() -> jugador.consumirRecursos(precio), "Despues del segundo poblado deberia poder pagar un mineral");
    }
}
