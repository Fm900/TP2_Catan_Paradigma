package edu.fiuba.algo3.entrega_2;
import edu.fiuba.algo3.modelo.Intercambio.Banca;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Cartas.Carta;
import edu.fiuba.algo3.modelo.Jugador.MazoDeRecursos;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Mano;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Constructores.GeneradorDeTerrenos;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice.Vertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsumoDeRecursosYCambioDePVAlMejorarACiudad {

    private Jugador nuevoJugador() {
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<>());
        Mano mano = new Mano();
        return new Jugador(gestor, mano,"Alex");
    }

    private void darRecursosParaUnPobladoYCiudad(Jugador jugador) {
        jugador.agregarRecurso(new Madera(), 1);
        jugador.agregarRecurso(new Ladrillo(), 1);
        jugador.agregarRecurso(new Grano(), 1);
        jugador.agregarRecurso(new Lana(), 1);

        jugador.agregarRecurso(new Grano(), 2);
        jugador.agregarRecurso(new Mineral(), 3);
    }



    @Test
    void mejorarPobladoACiudadConsumeRecursos() {
        Jugador jugador = nuevoJugador();
        darRecursosParaUnPobladoYCiudad(jugador);

        Vertice vertice = new Vertice();

        // Construyo el poblado
        assertDoesNotThrow(() -> vertice.construirPobladoInicial(jugador));

        // Mejoro a ciudad
        assertDoesNotThrow(() -> vertice.mejorarPobladoACiudad(jugador));

        // si intento mejorar otra vez, no debería poder por falta de recursos
        assertThrows(RuntimeException.class, () -> vertice.mejorarPobladoACiudad(jugador));
    }

    @Test
    void mejorarPobladoACiudadActualizaPuntosDeVictoria() {
        MazoDeRecursos gestor = new MazoDeRecursos(new ArrayList<>());
        Mano mano = new Mano();
        Jugador jugador = new Jugador(gestor, mano,"Alex");

        // recursos para 1 poblado
        jugador.agregarRecurso(new Madera(), 1);
        jugador.agregarRecurso(new Ladrillo(), 1);
        jugador.agregarRecurso(new Grano(), 1);
        jugador.agregarRecurso(new Lana(), 1);

        // recursos para 1 ciudad
        jugador.agregarRecurso(new Grano(), 2);
        jugador.agregarRecurso(new Mineral(), 3);

        // crear tablero y juego
        Tablero tablero = new Tablero(new GeneradorDeTerrenos());
        List<Carta> cartas = new ArrayList<>();
        Banca banca = Banca.crearBanca(new ArrayList<>(), cartas);
        Juego juego = Juego.crearInstancia(List.of(jugador), new ArrayList<>(), new ArrayList<>(), tablero, banca);

        Vertice vertice = new Vertice();

        vertice.construirPobladoInicial(jugador);
        assertEquals(1,juego.calcularPuntosTotalesDe(jugador)); // con un poblado el jugador debe tener 1 punto

        vertice.mejorarPobladoACiudad(jugador);
        assertEquals(2,juego.calcularPuntosTotalesDe(jugador)); // con una ciudad el jugador debe tener 2 puntos
    }
}